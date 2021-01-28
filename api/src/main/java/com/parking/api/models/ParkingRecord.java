package com.parking.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class ParkingRecord {
    private @Id @GeneratedValue Long id;

    private Long slotId;
    private String carId;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSlotId() {
        return this.slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getCarId() {
        return this.carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }


}
