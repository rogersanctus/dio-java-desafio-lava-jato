package me.rogerioferreira.lavajato.presentation.dtos;

import java.time.OffsetTime;
import java.util.List;

public record WashingYardCreationDto(
    OffsetTime openingTime,
    OffsetTime closingTime,
    List<WashingPlaceDto> washingPlaces) {
}
