package com.maximeandre.jhipstertest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.maximeandre.jhipstertest.domain.Trip;

/**
 * Spring Data MongoDB repository for the {@link Trip} entity.
 */
@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
  Page<Trip> findAllByUserId(Pageable pageable, String userId);
}
