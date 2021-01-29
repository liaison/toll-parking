package com.parking.api.models;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called ReservationRepository
// CRUD refers Create, Read, Update, Delete
// public interface ReservationRepository extends CrudRepository<Reservation, Long> {}

public interface ReservationRepository extends JpaRepository<Reservation, Long> {}