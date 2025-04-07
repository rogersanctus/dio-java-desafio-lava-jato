package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.WashingService;

public interface WashingServiceRepository extends JpaRepository<WashingService, String> {
}
