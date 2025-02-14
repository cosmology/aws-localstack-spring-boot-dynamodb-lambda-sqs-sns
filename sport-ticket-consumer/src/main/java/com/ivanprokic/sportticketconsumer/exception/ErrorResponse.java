package com.ivanprokic.sportticketconsumer.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private OffsetDateTime timestamp;

  private List<String> message;

  public ErrorResponse(List<String> message) {
    this.timestamp = OffsetDateTime.now(Clock.systemUTC());
    this.message = message;
  }
}
