package com.ivanprokic.ticketproducer.service;

import com.ivanprokic.ticketproducer.model.Ticket;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Component;

@Component
public class RandomTicketGenerator {

  public Ticket getRandomly() {
    // Pick a random title from the list
    String selectedEvent = TITLES.get(ThreadLocalRandom.current().nextInt(TITLES.size()));

    // Determine the event type based on the content of the title
    String eventType = determineEventType(selectedEvent);

    // Create and return the ticket with the appropriate event type
    return new Ticket(selectedEvent, eventType);
  }

  private String determineEventType(String title) {
    if (title.matches(".*@.*(Arena|Stadium|Field|Center|Garden).*")) {
      return "sport"; // Sports-related titles
    } else if (title.matches(".*@.*(AMC|Cinema|Theatre|Film|IMAX).*")
        || title.toLowerCase().contains("movie")) {
      return "movie"; // Movie-related titles
    } else if (title.matches(".*@.*(Restaurant|Cafe|Bar|Grill).*")
        || title.toLowerCase().contains("restaurant")) {
      return "food"; // Restaurant-related titles
    }
    return "unknown"; // Default if no match is found
  }

  private static final DateTimeFormatter DATETIME_FORMATTER =
      DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

  private static String generateEvent(String description, int daysFromNow) {
    OffsetDateTime eventDate = OffsetDateTime.now(Clock.systemUTC()).plusDays(daysFromNow);
    return description + " - " + eventDate.format(DATETIME_FORMATTER);
  }

  private static final List<String> TITLES =
      List.of(

          // Sports Events
          generateEvent("LA Kings - Anaheim Ducks @ Honda Center", 2),
          generateEvent("Chicago Bulls - Miami Heat @ United Center", 3),
          generateEvent("New York Giants - Dallas Cowboys @ MetLife Stadium", 4),
          generateEvent("Boston Celtics - Golden State Warriors @ TD Garden", 5),
          generateEvent("Green Bay Packers - Detroit Lions @ Lambeau Field", 6),
          generateEvent("New York Rangers - Pittsburgh Penguins @ Madison Square Garden", 7),
          generateEvent("Seattle Seahawks - San Francisco 49ers @ Lumen Field", 8),
          generateEvent("Denver Nuggets - Phoenix Suns @ Ball Arena", 9),
          generateEvent("Toronto Maple Leafs - Montreal Canadiens @ Scotiabank Arena", 10),
          generateEvent("Philadelphia Eagles - Kansas City Chiefs @ Lincoln Financial Field", 11),
          generateEvent("Los Angeles Lakers - Houston Rockets @ Crypto.com Arena", 12),
          generateEvent("Tampa Bay Buccaneers - Atlanta Falcons @ Raymond James Stadium", 13),

          // Movie Events
          generateEvent("Oppenheimer @ AMC Lincoln Square 13, New York", 2),
          generateEvent("Barbie @ TCL Chinese Theatre, Los Angeles", 3),
          generateEvent("Killers of the Flower Moon @ Alamo AMC, Austin", 4),
          generateEvent("Dune: Part Two @ Cineplex Scotiabank Theatre, Toronto", 5),
          generateEvent("The Marvels @ Odeon Luxe Leicester Square Theatre, London", 6),
          generateEvent(
              "The Hunger Games: The Ballad of Songbirds and Snakes @ IMAX Theatre, San Francisco",
              7),
          generateEvent("Wish @ AMC The Grove 14, Los Angeles", 8),
          generateEvent("Napoleon @ Vue West End Theatre, London", 9),
          generateEvent(
              "Spider-Man: Across the Spider-Verse @ Pacific Theatre at the Americana, Glendale",
              10),
          generateEvent("The Flash @ Showcase Cinema de Lux, Boston", 11),
          generateEvent("Elemental @ Angelika Film Center, Dallas", 12),
          generateEvent("The Super Mario Bros. Movie @ ArcLight Cinema, Chicago", 13));
}
