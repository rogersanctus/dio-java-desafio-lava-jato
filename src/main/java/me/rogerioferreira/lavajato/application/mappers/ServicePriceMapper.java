package me.rogerioferreira.lavajato.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.presentation.dtos.ServicePriceDto;

@Mapper(componentModel = "spring")
public interface ServicePriceMapper {
  @Mapping(target = "id", ignore = true)
  ServicePrice toModel(ServicePriceDto servicePriceDto);

  ServicePriceDto toDto(ServicePrice servicePrice);
}
