package me.rogerioferreira.lavajato.presentation.dtos;

import java.time.OffsetTime;
import java.util.List;

import me.rogerioferreira.lavajato.domain.entities.WashingPlace;

public record WashingYardDto(
    OffsetTime openingTime,
    OffsetTime closingTime,
    List<WashingPlace> washingPlaces) {
}
