package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
