package me.rogerioferreira.lavajato.application;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import me.rogerioferreira.lavajato.application.services.WashingYardService;
import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.domain.entities.Vehicle;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;
import me.rogerioferreira.lavajato.domain.enums.VehicleType;
import me.rogerioferreira.lavajato.domain.repositories.OperatorRespository;
import me.rogerioferreira.lavajato.domain.repositories.ServicePriceRepository;
import me.rogerioferreira.lavajato.domain.repositories.VehicleRepository;
import me.rogerioferreira.lavajato.presentation.dtos.WashingPlaceDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingYardCreationDto;

@Component
public class Seeder implements CommandLineRunner {
  @Autowired
  private OperatorRespository operatorRespository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private ServicePriceRepository servicePriceRepository;

  @Autowired
  private WashingYardService washingYardService;

  @Override
  public void run(String... args) throws Exception {
    var operator = new Operator(null, "John Doe");
    var vehicle = new Vehicle(null, VehicleType.CAR, "Markinson Prutti", "XYZ-23A3", "Red", "Mustang", "Ford");
    var servicePrice1 = new ServicePrice(null, ServiceType.BASIC, BigDecimal.valueOf(5.00));
    var servicePrice2 = new ServicePrice(null, ServiceType.COMPLETE, BigDecimal.valueOf(15.00));

    var openingTime = OffsetTime.parse("07:00+03:00", DateTimeFormatter.ISO_OFFSET_TIME);
    var closingTime = OffsetTime.parse("17:00+03:00");
    var washingYard = new WashingYardCreationDto(
        openingTime,
        closingTime,
        List.of(
            new WashingPlaceDto(1),
            new WashingPlaceDto(2)));

    operatorRespository.save(operator);
    vehicleRepository.save(vehicle);
    servicePriceRepository.save(servicePrice1);
    servicePriceRepository.save(servicePrice2);
    washingYardService.save(washingYard);

    System.out.println("Seeders executados");
  }

}
