package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.ServicePrice;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, String> {
}
