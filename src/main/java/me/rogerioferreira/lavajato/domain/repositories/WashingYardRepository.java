package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.WashingYard;

public interface WashingYardRepository extends JpaRepository<WashingYard, String> {
}
