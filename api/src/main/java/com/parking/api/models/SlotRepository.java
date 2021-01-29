package com.parking.api.models;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SlotRepository
public interface SlotRepository extends JpaRepository<Slot, Long> {
}