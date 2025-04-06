package me.rogerioferreira.lavajato.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.presentation.dtos.WashingPlaceDto;

@Mapper(componentModel = "spring")
public interface WashingPlaceMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "washingYardId", ignore = true)
  public WashingPlace toModel(WashingPlaceDto washingPlaceDto);

  public WashingPlaceDto toDto(WashingPlace washingPlace);
}
