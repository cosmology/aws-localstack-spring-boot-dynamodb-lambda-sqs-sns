package com.ivanprokic.sportticketconsumer.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(name = "sports")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Sport {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String traceId;
  @Column private String title;
  @Column private String eventType;
  @Column private OffsetDateTime publishedAt;
}
