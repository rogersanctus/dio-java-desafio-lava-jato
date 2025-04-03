package me.rogerioferreira.lavajato.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.rogerioferreira.lavajato.domain.entities.Operator;

public interface OperatorRespository extends JpaRepository<Operator, String> {
}
