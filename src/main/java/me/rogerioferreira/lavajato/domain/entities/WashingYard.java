package me.rogerioferreira.lavajato.domain.entities;

import java.time.OffsetTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import me.rogerioferreira.lavajato.infra.models.WithId;

@Entity
@Data
public class WashingYard implements WithId {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Size(min = 1)
  @OneToMany(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "washing_yard_id")
  private List<WashingPlace> washingPlaces;

  @NotNull
  private OffsetTime openingTime;

  @NotNull
  // TODO: Validate if the closing time is after the opening time
  private OffsetTime closingTime;
}
