package me.rogerioferreira.lavajato.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;

@Entity
@Data
public class ServicePrice {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull
  private ServiceType serviceType;

  @NotNull
  @DecimalMin(value = "0.00")
  @Digits(integer = 10, fraction = 2)
  private BigDecimal price;
}
