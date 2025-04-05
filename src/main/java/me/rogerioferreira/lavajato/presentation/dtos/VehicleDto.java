package me.rogerioferreira.lavajato.presentation.dtos;

import me.rogerioferreira.lavajato.domain.enums.VehicleType;

public record VehicleDto(
    VehicleType vehicleType,
    String ownerName,
    String licensePlate,
    String color,
    String model,
    String brand) {
}
