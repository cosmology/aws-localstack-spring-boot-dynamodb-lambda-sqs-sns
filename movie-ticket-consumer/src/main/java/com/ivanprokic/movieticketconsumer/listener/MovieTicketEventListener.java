package com.ivanprokic.movieticketconsumer.listener;

import com.ivanprokic.movieticketconsumer.entity.Movie;
import com.ivanprokic.movieticketconsumer.event.TicketEvent;
import com.ivanprokic.movieticketconsumer.repository.MovieRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.micrometer.observation.annotation.Observed;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MovieTicketEventListener {

  private final MovieRepository movieRepository;

  @Value("${aws.sqs.destination}")
  private String awsSqsDestination;

  private final SimpMessagingTemplate simpMessagingTemplate;

  @SqsListener("${aws.sqs.destination}")
  @Observed(
      name = "movie-ticket-consumer:sqsListener",
      contextualName = "movie-ticket-consumer:sqsListener",
      lowCardinalityKeyValues = {"locale", "en-US"})
  public void sqsListener(TicketEvent ticketEvent) {
    log.info("MovieTicketEventListener received ticketEvent: {}" + ticketEvent);

    // Send the raw payload to the WebSocket topic
    simpMessagingTemplate.convertAndSend("/topic/ticket", ticketEvent);

    // Extract and set values for the Movie entity
    var ticket = ticketEvent.ticket();
    var movieBuilder =
        Movie.builder()
            .traceId(ticket.traceId())
            .eventType(ticket.eventType())
            .title(ticket.title())
            .publishedAt(OffsetDateTime.parse(ticketEvent.ticket().publishedAt().toString()));

    // Save to the repository
    movieRepository.save(movieBuilder.build());
  }
}
