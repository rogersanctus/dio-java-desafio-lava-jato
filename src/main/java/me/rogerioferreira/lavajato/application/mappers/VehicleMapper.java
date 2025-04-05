package me.rogerioferreira.lavajato.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import me.rogerioferreira.lavajato.domain.entities.Vehicle;
import me.rogerioferreira.lavajato.presentation.dtos.VehicleDto;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
  @Mapping(target = "id", ignore = true)
  Vehicle toModel(VehicleDto operatorDto);

  VehicleDto toDto(Vehicle operator);
}
