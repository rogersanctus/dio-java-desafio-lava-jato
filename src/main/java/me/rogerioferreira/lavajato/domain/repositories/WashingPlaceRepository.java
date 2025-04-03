package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.WashingPlace;

public interface WashingPlaceRepository extends JpaRepository<WashingPlace, String> {
}
