package me.rogerioferreira.lavajato.domain.entities;

import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class WashingYard {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Size(min = 1)
  @OneToMany()
  @JoinColumn(name = "washing_yard_id")
  private List<WashingPlace> washingPlaces;

  @NotNull
  private OffsetDateTime openingTime;

  @NotNull
  // TODO: Validate if the closing time is after the opening time
  private OffsetDateTime closingTime;
}
