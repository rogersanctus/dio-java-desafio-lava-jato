package me.rogerioferreira.lavajato.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.rogerioferreira.lavajato.domain.entities.WashingYard;
import me.rogerioferreira.lavajato.domain.repositories.WashingYardRepository;
import me.rogerioferreira.lavajato.infra.services.WashingYardService;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardCreationDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardDto;

@RestController
@RequestMapping("/washing-yards")
public class WashingYardController {
  @Autowired
  private WashingYardRepository washingYardRepository;

  @Autowired
  private WashingYardService washingYardService;

  @GetMapping()
  public ResponseEntity<List<WashingYard>> getAll() {
    return ResponseEntity.ok().body(washingYardRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WashingYard> getById(@PathVariable("id") String id) {
    var mayBeWashingYard = washingYardRepository.findById(id);

    if (mayBeWashingYard.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(mayBeWashingYard.get());
  }

  @PostMapping()
  public ResponseEntity<WashingYard> create(@RequestBody WashingYardCreationDto washingYardDCreationDto) {
    var savedModel = washingYardService.save(washingYardDCreationDto);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(washingYardRepository.save(savedModel));
  }

  @PutMapping("/{id}")
  public ResponseEntity<WashingYard> update(@PathVariable("id") String id, @RequestBody WashingYardDto washingYardDto) {
    var model = this.washingYardService.save(id, washingYardDto);

    if (model == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(washingYardRepository.save(model));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") String id) {
    if (!this.washingYardRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    washingYardRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
