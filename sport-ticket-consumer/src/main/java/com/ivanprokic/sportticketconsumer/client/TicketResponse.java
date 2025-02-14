package com.ivanprokic.sportticketconsumer.client;

import java.time.OffsetDateTime;

public record TicketResponse(
    String id, String eventType, String title, OffsetDateTime publishedAt) {}
