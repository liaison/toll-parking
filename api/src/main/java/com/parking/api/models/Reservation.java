package com.parking.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reservation {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private Long slotId;
    private String carId;
    private Integer billingPolicy;
    private LocalDateTime checkinDatetime;

    public Reservation() {}

    public Reservation(Long slotId, String carId, Integer billingPolicy) {
        this.slotId = slotId;
        this.carId = carId;
        this.billingPolicy = billingPolicy;
        this.checkinDatetime = LocalDateTime.now();
    }

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

    public Integer getBillingPolicy() {
        return this.billingPolicy;
    }

    public void setBillingPolicy(Integer billingPolicy) {
        this.billingPolicy = billingPolicy;
    }

    public LocalDateTime getCheckinDatetime() {
        return this.checkinDatetime;
    }

    public void setCheckinDatetime(LocalDateTime checkinDatetime) {
        this.checkinDatetime = checkinDatetime;
    }

    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Reservation))
        return false;
      Reservation reservation = (Reservation) o;
      return Objects.equals(this.id, reservation.id)
          && Objects.equals(this.carId, reservation.carId)
          && Objects.equals(this.checkinDatetime, reservation.checkinDatetime)
          && Objects.equals(this.billingPolicy, reservation.billingPolicy);
    }

}
