package me.rogerioferreira.lavajato.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.domain.repositories.OperatorRespository;

@RestController
@RequestMapping("/operators")
public class OperatorController {
  @Autowired
  private OperatorRespository operatorRepository;

  @GetMapping
  public ResponseEntity<List<Operator>> getAll() {
    return ResponseEntity.ok().body(operatorRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Operator> getById(String id) {
    var mayBeOperator = operatorRepository.findById(id);

    if (mayBeOperator.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(mayBeOperator.get());
  }

  @PostMapping
  public ResponseEntity<Operator> create(@Valid @RequestBody Operator operator) {
    return ResponseEntity.ok().body(operatorRepository.save(operator));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Operator> update(@Valid @RequestBody Operator operator) {
    return ResponseEntity.ok().body(operatorRepository.save(operator));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(String id) {
    operatorRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
