package me.rogerioferreira.lavajato.application.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.presentation.dtos.OperatorDto;

@Mapper(componentModel = "spring")
public interface OperatorMapper {
  @Mapping(target = "id", ignore = true)
  Operator toOperator(OperatorDto operatorDto);

  OperatorDto toOperatorDto(Operator operator);
}
