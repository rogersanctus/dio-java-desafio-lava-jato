package me.rogerioferreira.lavajato.application.services;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.rogerioferreira.lavajato.domain.entities.Operator;
import me.rogerioferreira.lavajato.domain.entities.Vehicle;
import me.rogerioferreira.lavajato.domain.entities.WashingPlace;
import me.rogerioferreira.lavajato.domain.entities.WashingService;
import me.rogerioferreira.lavajato.domain.enums.ServiceType;
import me.rogerioferreira.lavajato.domain.enums.WashingServiceStatus;
import me.rogerioferreira.lavajato.domain.exceptions.EntityNotFoundException;
import me.rogerioferreira.lavajato.domain.repositories.OperatorRespository;
import me.rogerioferreira.lavajato.domain.repositories.ServicePriceRepository;
import me.rogerioferreira.lavajato.domain.repositories.VehicleRepository;
import me.rogerioferreira.lavajato.domain.repositories.WashingPlaceRepository;
import me.rogerioferreira.lavajato.domain.repositories.WashingServiceRepository;
import me.rogerioferreira.lavajato.domain.rules.ConstraintsValidator;
import me.rogerioferreira.lavajato.presentation.dtos.ObjectWithOnlyIdDto;
import me.rogerioferreira.lavajato.presentation.dtos.WashingServiceCreationDto;

@Service
public class WashingServiceService {
  @Autowired
  private WashingServiceRepository washingServiceRepository;

  @Autowired
  private OperatorRespository operatorRespository;

  @Autowired
  private VehicleRepository vehicleRespository;

  @Autowired
  private WashingPlaceRepository washingPlaceRepository;

  @Autowired
  private ServicePriceRepository servicePriceRepository;

  @Autowired
  private ConstraintsValidator validator;

  public WashingService save(WashingServiceCreationDto washingServiceCreationDto) {
    var operator = this.getOperator(washingServiceCreationDto.operator());
    var vehicle = this.getVechicle(washingServiceCreationDto.vehicle());
    var washingPlace = this.getWashingPlace(washingServiceCreationDto.washingPlace());

    var price = this.getPriceForServiceType(washingServiceCreationDto.serviceType());
    var serviceTime = OffsetDateTime.now();

    var washingService = new WashingService(
        UUID.randomUUID().toString(),
        operator,
        vehicle,
        washingPlace,
        washingServiceCreationDto.serviceType(),
        WashingServiceStatus.WAITING,
        serviceTime,
        price);

    this.validator.validate(washingService);

    return this.washingServiceRepository.save(washingService);
  }

  private Operator getOperator(ObjectWithOnlyIdDto operator) throws EntityNotFoundException {
    if (!this.operatorRespository.existsById(operator.id())) {
      throw new EntityNotFoundException("Operator", "operator", operator.id());
    }

    return new Operator(operator.id(), null);
  }

  private Vehicle getVechicle(ObjectWithOnlyIdDto vehicle) throws EntityNotFoundException {
    if (!this.vehicleRespository.existsById(vehicle.id())) {
      throw new EntityNotFoundException("Vehicle", "vehicle", vehicle.id());
    }

    return new Vehicle(vehicle.id(), null, null, null, null, null, null);
  }

  private WashingPlace getWashingPlace(ObjectWithOnlyIdDto washingPlace) throws EntityNotFoundException {
    if (!this.washingPlaceRepository.existsById(washingPlace.id())) {
      throw new EntityNotFoundException("WashingPlace", "washingPlace", washingPlace.id());
    }

    return new WashingPlace(washingPlace.id(), null, null);
  }

  private BigDecimal getPriceForServiceType(ServiceType serviceType) {
    var mayBePrice = this.servicePriceRepository.findPriceByServiceType(serviceType);

    if (mayBePrice.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("No price found in ServicePrice for Service type: %s", serviceType));
    }

    return mayBePrice.get();
  }

}
