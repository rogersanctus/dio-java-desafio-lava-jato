package me.rogerioferreira.lavajato.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import me.rogerioferreira.lavajato.application.mappers.WashingPlaceMapper;
import me.rogerioferreira.lavajato.application.mappers.WashingYardMapper;
import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.domain.entities.WashingYard;
import me.rogerioferreira.lavajato.domain.repositories.WashingYardRepository;
import me.rogerioferreira.lavajato.domain.rules.ConstraintsValidator;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardCreationDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardDto;

@Service
public class WashingYardService {
  @Autowired
  private WashingYardRepository washingYardRepository;

  @Autowired
  private ConstraintsValidator validator;

  @Autowired
  private WashingYardMapper mapper;

  @Autowired
  WashingPlaceMapper placeMapper;

  @Autowired
  private WashingPlaceService washingPlaceService;

  @Transactional
  public WashingYard save(WashingYardCreationDto washingYardDto)
      throws IllegalArgumentException, ConstraintViolationException {
    var places = washingYardDto
        .washingPlaces()
        .stream()
        .map((dto) -> placeMapper.toModel(dto))
        .toList();

    var yardId = UUID.randomUUID().toString();
    var model = this.mapper.toModel(washingYardDto);
    model.setId(yardId);
    model.setWashingPlaces(null);

    this.validator.validate(model);

    var savedModel = this.washingYardRepository.save(model);

    var savedPlaces = savePlaces(places, yardId);
    savedModel.setWashingPlaces(new ArrayList<WashingPlace>(savedPlaces));

    return savedModel;
  }

  @Transactional
  public WashingYard save(String id, WashingYardDto washingYardDto)
      throws IllegalArgumentException, ConstraintViolationException {
    if (!this.washingYardRepository.existsById(id)) {
      return null;
    }

    var places = washingYardDto.washingPlaces();

    savePlaces(places, id);

    var model = this.mapper.toModel(washingYardDto);
    model.setId(id);
    model.getWashingPlaces().addAll(places);

    this.validator.validate(model);

    var savedModel = this.washingYardRepository.save(model);

    return savedModel;
  }

  @Transactional
  public List<WashingPlace> savePlaces(List<WashingPlace> places, String yardId) {
    return places
        .stream()
        .map(place -> {
          place.setWashingYardId(yardId);
          return this.washingPlaceService.save(place);
        })
        .toList();
  }
}
