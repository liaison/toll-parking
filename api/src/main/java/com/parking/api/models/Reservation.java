package com.parking.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    private @Id @GeneratedValue Long id;
    private Long slotId;
    private String carId;
    private LocalDateTime checkinDatetime;

    Reservation() {}

    public Reservation(Long slotId, String carId) {
        this.slotId = slotId;
        this.carId = carId;
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

    public LocalDateTime getCheckDatetime() {
        return this.checkinDatetime;
    }

    public void setCheckDatetime(LocalDateTime checkinDatetime) {
        this.checkinDatetime = checkinDatetime;
    }
}
