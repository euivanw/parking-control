package dev.ivanwilhelm.parkingcontrol.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private UUID id;

    @Column(nullable = false, unique = true, length = 10, name = "PARKING_SPOT_NUMBER")
    private String parkingSpotNumber;

    @Column(nullable = false, unique = true, length = 7, name = "LICENSE_PLATE_CAR")
    private String licensePlateCar;
    
    @Column(nullable = false, length = 70, name = "BRAND_CAR")
    private String brandCar;

    @Column(nullable = false, length = 70, name = "MODEL_CAR")
    private String modelCar;

    @Column(nullable = false, length = 70, name = "COLOR_CAR")
    private String colorCar;

    @Column(nullable = false, name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 130, name = "RESPONSIBLE_NAME")
    private String responsibleName;

    @Column(nullable = false, length = 30, name = "APARTMENT")
    private String apartment;

    @Column(nullable = false, length = 30, name = "BLOCK")
    private String block;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getResponsibleName() {
        return responsibleName;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
