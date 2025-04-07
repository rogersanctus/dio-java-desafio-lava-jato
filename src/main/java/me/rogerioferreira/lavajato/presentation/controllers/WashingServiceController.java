package me.rogerioferreira.lavajato.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.rogerioferreira.lavajato.application.services.WashingServiceService;
import me.rogerioferreira.lavajato.domain.entities.WashingService;
import me.rogerioferreira.lavajato.domain.repositories.WashingServiceRepository;
import me.rogerioferreira.lavajato.presentation.dtos.WashingServiceCreationDto;

@RestController
@RequestMapping("/washing-services")
public class WashingServiceController {
  @Autowired
  private WashingServiceService washingServiceService;

  @Autowired
  private WashingServiceRepository washingServiceRepository;

  @GetMapping()
  public ResponseEntity<List<WashingService>> getAll() {
    return ResponseEntity.ok().body(washingServiceRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<WashingService> getById(String id) {
    var mayBeWashingService = washingServiceRepository.findById(id);

    if (mayBeWashingService.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(mayBeWashingService.get());
  }

  @PostMapping()
  public ResponseEntity<WashingService> create(@RequestBody WashingServiceCreationDto washingService) {
    var model = this.washingServiceService.save(washingService);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(washingServiceRepository.save(model));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(String id) {
    if (!this.washingServiceRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    washingServiceRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
