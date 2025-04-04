package me.rogerioferreira.lavajato.domain.entities;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import me.rogerioferreira.lavajato.infra.models.WithId;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;
import me.rogerioferreira.lavajato.domain.enums.WashingServiceStatus;

@Entity
@Data
public class WashingService implements WithId {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @NotNull
  @OneToOne
  private Operator operator;

  @NotNull
  @OneToOne
  private Vehicle vehicle;

  @NotNull
  @ManyToOne
  private WashingPlace washingPlace;

  @NotNull
  private ServiceType serviceType;

  @NotNull
  private WashingServiceStatus status = WashingServiceStatus.WAITING;

  @NotNull
  private OffsetDateTime serviceTime;

  @NotNull
  @DecimalMin(value = "0.00")
  private BigDecimal totalToPay;
}
