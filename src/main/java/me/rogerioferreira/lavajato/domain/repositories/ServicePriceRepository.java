package me.rogerioferreira.lavajato.domain.repositories;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, String> {
  @Query("SELECT p.price FROM ServicePrice p WHERE p.serviceType = :serviceType")
  Optional<BigDecimal> findPriceByServiceType(@Param("serviceType") ServiceType serviceType);
}
