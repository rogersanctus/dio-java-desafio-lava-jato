package me.rogerioferreira.lavajato.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import me.rogerioferreira.lavajato.infra.models.WithId;
import me.rogerioferreira.lavajato.domain.enums.VehicleType;
import me.rogerioferreira.lavajato.domain.rules.Constraints;

@Entity
@Data
public class Vehicle implements WithId {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull
  private VehicleType vehicleType;

  @NotNull
  @Size(min = Constraints.MIN_NAME_LENGTH, max = Constraints.MAX_NAME_LENGTH)
  private String ownerName;

  @NotBlank
  private String licensePlate;

  @NotBlank
  private String color;

  @NotBlank
  private String model;

  @NotBlank
  private String brand;
}
