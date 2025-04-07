package me.rogerioferreira.lavajato.application;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.domain.entities.ServicePrice;
import me.rogerioferreira.lavajato.domain.entities.Vehicle;
import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.domain.entities.WashingYard;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;
import me.rogerioferreira.lavajato.domain.enums.VehicleType;
import me.rogerioferreira.lavajato.domain.repositories.OperatorRespository;
import me.rogerioferreira.lavajato.domain.repositories.ServicePriceRepository;
import me.rogerioferreira.lavajato.domain.repositories.VehicleRepository;
import me.rogerioferreira.lavajato.domain.repositories.WashingPlaceRepository;
import me.rogerioferreira.lavajato.domain.repositories.WashingYardRepository;

@Component
public class Seeder implements CommandLineRunner {
  @Autowired
  private OperatorRespository operatorRespository;

  @Autowired
  private VehicleRepository vehicleRepository;

  @Autowired
  private ServicePriceRepository servicePriceRepository;

  @Autowired
  private WashingYardRepository washingYardRepository;

  @Autowired
  private WashingPlaceRepository washingPlaceRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    var operator = new Operator("1", "John Doe");

    var vehicle = new Vehicle("1", VehicleType.CAR, "Markinson Prutti", "XYZ-23A3", "Red", "Mustang", "Ford");
    var servicePrice1 = new ServicePrice("1", ServiceType.BASIC, BigDecimal.valueOf(5.00));
    var servicePrice2 = new ServicePrice("2", ServiceType.COMPLETE, BigDecimal.valueOf(15.00));

    var openingTime = OffsetTime.parse("07:00+03:00", DateTimeFormatter.ISO_OFFSET_TIME);
    var closingTime = OffsetTime.parse("17:00+03:00");

    var washingYard = new WashingYard("1", null, openingTime, closingTime);

    var washingPlace1 = new WashingPlace("1", "1", 1);
    var washingPlace2 = new WashingPlace("2", "1", 2);

    washingYardRepository.save(washingYard);
    washingPlaceRepository.save(washingPlace1);
    washingPlaceRepository.save(washingPlace2);

    operatorRespository.save(operator);
    vehicleRepository.save(vehicle);
    servicePriceRepository.save(servicePrice1);
    servicePriceRepository.save(servicePrice2);

    System.out.println("Seeders executados");
  }

}
