package me.rogerioferreira.lavajato.domain.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;
import me.rogerioferreira.lavajato.infra.models.WithId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePrice implements WithId {
  @Id
  private String id;

  @NotNull
  private ServiceType serviceType;

  @NotNull
  @DecimalMin(value = "0.00")
  @Digits(integer = 10, fraction = 2)
  private BigDecimal price;
}
