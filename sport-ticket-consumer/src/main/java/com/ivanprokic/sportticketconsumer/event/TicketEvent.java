package com.ivanprokic.sportticketconsumer.event;

import java.time.OffsetDateTime;

public record TicketEvent(String action, Ticket ticket) {
  public record Ticket(
      String id, String traceId, String eventType, String title, OffsetDateTime publishedAt) {}
}
