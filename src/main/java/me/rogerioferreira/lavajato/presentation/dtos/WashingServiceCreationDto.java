package me.rogerioferreira.lavajato.presentation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import me.rogerioferreira.lavajato.domain.enums.ServiceType;

public record WashingServiceCreationDto(
    @JsonProperty("operator") ObjectWithOnlyIdDto operator,
    @JsonProperty("vehicle") ObjectWithOnlyIdDto vehicle,
    @JsonProperty("washingPlace") ObjectWithOnlyIdDto washingPlace,
    ServiceType serviceType) {
}
