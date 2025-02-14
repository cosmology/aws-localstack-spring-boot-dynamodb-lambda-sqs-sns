package com.ivanprokic.movieticketconsumer.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String traceId;
  @Column private String title;
  @Column private String eventType;
  @Column private OffsetDateTime publishedAt;
}
