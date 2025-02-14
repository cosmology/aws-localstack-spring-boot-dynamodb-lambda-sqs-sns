package com.ivanprokic.movieticketconsumer.client;

import java.time.OffsetDateTime;

public record TicketResponse(
    String id, String eventType, String title, OffsetDateTime publishedAt) {}
