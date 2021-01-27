package com.parking.api;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
class ParkingSlot {

  private @Id @GeneratedValue Long id;
  private String type;
  private Integer billingPolicy;


  ParkingSlot() {}

  ParkingSlot(String type, Integer billingPolicy) {
    this.type = type;
    this.billingPolicy= billingPolicy;
  }

  public Long getId() {
    return this.id;
  }

  public String getType() {
    return this.type;
  }

  public Integer getBillingPolicy() {
    return this.billingPolicy;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setBillingPolicy(Integer billingPolicy) {
    this.billingPolicy = billingPolicy;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof ParkingSlot))
      return false;
    ParkingSlot slot = (ParkingSlot) o;
    return Objects.equals(this.id, slot.id)
        && Objects.equals(this.type, slot.type)
        && Objects.equals(this.billingPolicy, slot.billingPolicy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.type, this.billingPolicy);
  }

  @Override
  public String toString() {
    return "ParkingSlot{" + "id=" + this.id + ", type='" + this.type + '\''
          + ", billingPolicy='" + this.billingPolicy + '\'' + '}';
  }
}

