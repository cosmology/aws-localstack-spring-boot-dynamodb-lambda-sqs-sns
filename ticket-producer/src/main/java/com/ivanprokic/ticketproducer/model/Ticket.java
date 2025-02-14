package com.ivanprokic.ticketproducer.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Slf4j
@Data
@NoArgsConstructor
@DynamoDbBean
public class Ticket {
  private String id;
  private String traceId;
  private String eventType;
  private String title;
  private OffsetDateTime publishedAt;

  @DynamoDbPartitionKey
  public String getId() {
    return id;
  }

  public Ticket(String title, String eventType) {

    this.id = UUID.randomUUID().toString();
    this.eventType = eventType;
    this.title = title;
    this.publishedAt = OffsetDateTime.of(LocalDateTime.now(Clock.systemUTC()), ZoneOffset.UTC);
  }
}
