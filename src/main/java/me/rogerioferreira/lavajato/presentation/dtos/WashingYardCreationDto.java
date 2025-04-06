package me.rogerioferreira.lavajato.presentation.dtos;

import java.time.OffsetDateTime;
import java.util.List;

public record WashingYardCreationDto(
    OffsetDateTime openingTime,
    OffsetDateTime closingTime,
    List<WashingPlaceDto> washingPlaces) {
}
