package com.parking.api.models;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called BillingRepository
// CRUD refers Create, Read, Update, Delete
public interface BillingRepository extends CrudRepository<Billing, Long> {
}