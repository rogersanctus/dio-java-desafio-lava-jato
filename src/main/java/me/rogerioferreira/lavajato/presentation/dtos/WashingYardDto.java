package me.rogerioferreira.lavajato.presentation.dtos;

import java.time.OffsetDateTime;
import java.util.List;

import me.rogerioferreira.lavajato.domain.entities.WashingPlace;

public record WashingYardDto(
    OffsetDateTime openingTime,
    OffsetDateTime closingTime,
    List<WashingPlace> washingPlaces) {
}
