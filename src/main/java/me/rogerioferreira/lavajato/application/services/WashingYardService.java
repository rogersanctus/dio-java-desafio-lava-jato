package me.rogerioferreira.lavajato.application.services;

import java.util.ArrayList;
import java.util.List;

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

    var model = this.mapper.toModel(washingYardDto);
    model.setWashingPlaces(null);

    this.validator.validate(model);

    var savedModel = this.washingYardRepository.save(model);

    savePlaces(places, savedModel.getId());
    savedModel.setWashingPlaces(new ArrayList<WashingPlace>(places));

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
  public void savePlaces(List<WashingPlace> places, String yardId) {
    if (places.size() > 0) {
      for (var place : places) {
        place.setWashingYardId(yardId);
        this.washingPlaceService.save(place);
      }
    }
  }
}
