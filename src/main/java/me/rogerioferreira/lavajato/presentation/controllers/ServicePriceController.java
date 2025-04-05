package me.rogerioferreira.lavajato.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.rogerioferreira.lavajato.application.mappers.ServicePriceMapper;
import me.rogerioferreira.lavajato.application.utils.ConstraintsValidator;
import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.domain.repositories.ServicePriceRepository;
import me.rogerioferreira.lavajato.presentation.dtos.ServicePriceDto;

@RestController
@RequestMapping("/services-prices")
public class ServicePriceController {
  @Autowired
  private ServicePriceRepository vehicleRepository;

  @Autowired
  private ServicePriceMapper vehicleMapper;

  @Autowired
  private ConstraintsValidator validator;

  @GetMapping()
  public ResponseEntity<List<ServicePrice>> getAll() {
    return ResponseEntity.ok().body(vehicleRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ServicePrice> getById(String id) {
    var mayBeServicePrice = vehicleRepository.findById(id);

    if (mayBeServicePrice.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok().body(mayBeServicePrice.get());
  }

  @PostMapping()
  public ResponseEntity<ServicePrice> create(@RequestBody ServicePriceDto vehicle) {
    var model = this.vehicleMapper.toModel(vehicle);

    this.validator.validate(model);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(vehicleRepository.save(model));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ServicePrice> update(@RequestParam String id, @RequestBody ServicePriceDto vehicleDto) {
    if (!this.vehicleRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    var model = this.vehicleMapper.toModel(vehicleDto);

    this.validator.validate(model);

    model.setId(id);

    return ResponseEntity.ok().body(vehicleRepository.save(model));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(String id) {
    if (!this.vehicleRepository.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    vehicleRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
