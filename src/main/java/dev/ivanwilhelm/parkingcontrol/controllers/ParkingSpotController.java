package dev.ivanwilhelm.parkingcontrol.controllers;

import dev.ivanwilhelm.parkingcontrol.dtos.ParkingSpotDto;
import dev.ivanwilhelm.parkingcontrol.models.ParkingSpotModel;
import dev.ivanwilhelm.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use.");
        }

        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use.");
        }

        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block.");
        }

        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        var savedParkingSpotModel = parkingSpotService.save(parkingSpotModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedParkingSpotModel);
    }

    @GetMapping
    ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        var parkingSpotModelList = parkingSpotService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelList);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        var parkingSpotModel = parkingSpotService.findById(id);

        if (parkingSpotModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        var parkingSpotModel = parkingSpotService.findById(id);

        if (parkingSpotModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found.");
        }

        parkingSpotService.delete(parkingSpotModel.get());

        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot successfully deleted.");
    }
}
