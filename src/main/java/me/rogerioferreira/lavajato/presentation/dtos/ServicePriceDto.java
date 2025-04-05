package me.rogerioferreira.lavajato.presentation.dtos;

import java.math.BigDecimal;

import me.rogerioferreira.lavajato.domain.enums.ServiceType;

public record ServicePriceDto(ServiceType serviceType, BigDecimal price) {
}
