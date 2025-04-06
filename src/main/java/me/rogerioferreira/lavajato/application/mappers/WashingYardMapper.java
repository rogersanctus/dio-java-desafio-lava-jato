package me.rogerioferreira.lavajato.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.domain.entities.WashingYard;
import me.rogerioferreira.lavajato.presentation.dtos.WashingPlaceDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardCreationDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardDto;

@Mapper(componentModel = "spring")
public interface WashingYardMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "washingPlaces", expression = "java(new java.util.ArrayList<>())")
  WashingYard toModel(WashingYardDto washingYardDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "washingPlaces", expression = "java(new java.util.ArrayList<>())")
  WashingYard toModel(WashingYardCreationDto washingYardCreationDto);

  WashingYardDto toDto(WashingYard washingYard);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "washingYardId", ignore = true)
  WashingPlace toWashingPlace(WashingPlaceDto washingPlaceDto);
}
