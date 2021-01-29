package com.parking.api.models;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called BillingRepository
// CRUD refers Create, Read, Update, Delete
//public interface BillingRepository extends CrudRepository<Billing, Long> {

/**
 *  The JpaRepository has richer interfaces than the CrudRepository.
 */
public interface BillingRepository extends JpaRepository<Billing, Long> {
}