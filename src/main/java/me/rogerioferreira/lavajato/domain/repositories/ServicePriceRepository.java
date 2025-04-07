package me.rogerioferreira.lavajato.domain.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, String> {
  Optional<BigDecimal> findPriceByServiceType(ServiceType serviceType);
}
