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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.rogerioferreira.lavajato.application.mappers.OperatorMapper;
import me.rogerioferreira.lavajato.application.utils.ConstraintsValidator;
import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.domain.repositories.OperatorRespository;
import me.rogerioferreira.lavajato.presentation.dtos.OperatorDto;

@RestController
@RequestMapping("/operators")
public class OperatorController {
  @Autowired
  private OperatorRespository operatorRepository;

  @Autowired
  private OperatorMapper operatorMapper;

  @Autowired
  private ConstraintsValidator validator;

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
  public ResponseEntity<Operator> create(@RequestBody OperatorDto operatorDto) {
    var model = this.operatorMapper.toOperator(operatorDto);

    this.validator.validate(model);

    return ResponseEntity.ok().body(operatorRepository.save(model));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Operator> update(@RequestParam String id, @RequestBody OperatorDto operator) {
    if (!this.operatorRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    var model = this.operatorMapper.toOperator(operator);

    this.validator.validate(model);

    model.setId(id);

    return ResponseEntity.ok().body(operatorRepository.save(model));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(String id) {
    if (!this.operatorRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    operatorRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
