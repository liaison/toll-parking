package com.parking.api.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Slot {

  private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

  private String type;
  private Integer billingPolicy;
  private Boolean isAvailable;

  Slot() {}

  public Slot(String type, Integer billingPolicy) {
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

  public Boolean getIsAvailable() {
    return this.isAvailable;
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

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Slot))
      return false;
    Slot slot = (Slot) o;
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

