package me.rogerioferreira.lavajato.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import me.rogerioferreira.lavajato.application.mappers.WashingPlaceMapper;
import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.domain.exceptions.DuplicatedValueException;
import me.rogerioferreira.lavajato.domain.repositories.WashingPlaceRepository;
import me.rogerioferreira.lavajato.domain.rules.ConstraintsValidator;

@Service
public class WashingPlaceService {
  @Autowired
  private WashingPlaceRepository washingPlaceRepository;

  @Autowired
  WashingPlaceMapper mapper;

  @Autowired
  ConstraintsValidator validator;

  @Transactional
  public WashingPlace save(WashingPlace place) throws IllegalArgumentException, ConstraintViolationException {
    var exists = place.getId() != null
        // When updating, only consider as existing when the Id is different
        ? this.washingPlaceRepository.existsByPositionAndIdNot(place.getPosition(), place.getId())
        // When creating, any existing position must set exists to true
        : this.washingPlaceRepository.existsByPosition(place.getPosition());

    if (exists) {
      throw new DuplicatedValueException("position", place.getPosition());
    }

    if (place.getId() == null) {
      place.setId(UUID.randomUUID().toString());
    }

    this.validator.validate(place);
    return this.washingPlaceRepository.save(place);
  }
}
