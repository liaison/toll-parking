package com.parking.api.models;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Billing {
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private Long slotId;
    private String carId;
    private LocalDateTime checkinDatetime;
    private LocalDateTime checkoutDatetime;
    private Integer billingPolicy;
    private Double billingAmount;

    public Billing() {}

    public Billing(Long slotId, String carId, LocalDateTime checkinDatetime,
                   Integer billingPolicy) {
        this.slotId = slotId;
        this.carId = carId;

        this.checkinDatetime = checkinDatetime;
        this.checkoutDatetime = LocalDateTime.now();

        this.billingPolicy = billingPolicy;

        long minutes = ChronoUnit.MINUTES.between(this.checkinDatetime, this.checkoutDatetime);
        // Base on the parking duration and billing policy to calculate the cost.
        Double billingAmount = 0.0;
        if (this.billingPolicy == 1) {
            // fixed rate, proportional to the duration, unit (minute)
            Double rate = 0.10; // hardcode the rate, should be somewhere in the database as well.
            billingAmount = minutes * rate;
        } else if (this.billingPolicy == 2) {
            // fixed starting price plus proportional to the duration, unit (minute)
            Double rate = 0.05; // hardcode the rate, should be somewhere in the database as well.
            billingAmount = 3 + minutes * rate;
        }
        this.billingAmount = billingAmount;
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

    public LocalDateTime getCheckinDatetime() {
        return this.checkinDatetime;
    }

    public void setCheckinDatetime(LocalDateTime checkinDatetime) {
        this.checkinDatetime = checkinDatetime;
    }

    public LocalDateTime getCheckoutDatetime() {
        return this.checkoutDatetime;
    }

    public void setCheckoutDatetime(LocalDateTime checkoutDatetime) {
        this.checkoutDatetime = checkoutDatetime;
    }

    public Integer getBillingPolicy() {
        return this.billingPolicy;
    }

    public void setBillingPolicy(Integer billingPolicy) {
        this.billingPolicy = billingPolicy;
    }

    public Double getBillingAmount() {
        return this.billingAmount;
    }

    public void setBillingAmount(Double billingAmount) {
        this.billingAmount = billingAmount;
    }
}
