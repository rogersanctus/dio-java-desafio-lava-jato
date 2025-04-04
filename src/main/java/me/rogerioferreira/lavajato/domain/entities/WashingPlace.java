package me.rogerioferreira.lavajato.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.rogerioferreira.lavajato.infra.models.WithId;

@Entity
@Data
public class WashingPlace implements WithId {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull
  @Column(name = "washing_yard_id")
  private String washingYardId;

  @NotNull
  @Min(1)
  private Integer position;
}
