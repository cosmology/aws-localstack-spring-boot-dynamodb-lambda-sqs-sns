package com.ivanprokic.dynamodblambdafunction.publisher;

import org.springframework.stereotype.Component;

import com.ivanprokic.dynamodblambdafunction.event.TicketEvent;
import com.ivanprokic.dynamodblambdafunction.properties.AwsProperties;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TicketEventPublisher {

    private final SnsTemplate snsTemplate;
    private final AwsProperties awsProperties;

    private static final Logger logger = LoggerFactory.getLogger(TicketEventPublisher.class);

    public void publish(TicketEvent ticketEvent) {
        logger.info("TicketEventPublisher ticketEvent: {}" + ticketEvent);
        String topic = awsProperties.getSns().getDestination();
        snsTemplate.convertAndSend(topic, ticketEvent, Map.of("event_type", ticketEvent.ticket().eventType()));
    }
}