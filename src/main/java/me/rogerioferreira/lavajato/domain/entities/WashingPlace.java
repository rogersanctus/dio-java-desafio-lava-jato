package me.rogerioferreira.lavajato.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.rogerioferreira.lavajato.infra.models.WithId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashingPlace implements WithId {
  @Id
  private String id;

  @Column(name = "washing_yard_id")
  private String washingYardId;

  @NotNull
  @Min(1)
  @Column(unique = true)
  private Integer position;
}
