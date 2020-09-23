package com.maximeandre.jhipstertest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maximeandre.jhipstertest.config.Constants;
import com.maximeandre.jhipstertest.domain.Trip;
import com.maximeandre.jhipstertest.repository.TripRepository;
import com.maximeandre.jhipstertest.service.dto.TripDTO;
import com.maximeandre.jhipstertest.service.dto.UserDTO;

/**
 * Service class for managing trips.
 */
@Service
public class TripService {

  private final Logger log = LoggerFactory.getLogger(TripService.class);

  private final TripRepository tripRepository;

  public TripService(TripRepository tripRepository) {
    this.tripRepository = tripRepository;
  }

  /**
   * Create a new trip associated to the given userId
   * @param tripDTO
   * @param userId
   */
  public void createTrip(TripDTO tripDTO, String userId) {
    Trip trip = new Trip();

    trip.setUserId(userId);

    trip.setDeparturePlace(tripDTO.getDeparturePlace());
    trip.setArrivalPlace(tripDTO.getArrivalPlace());

    trip.setDepartureDate(tripDTO.getDepartureDate());
    trip.setArrivalDate(tripDTO.getArrivalDate());

    trip.setDepartureTime(tripDTO.getDepartureTime());
    trip.setArrivalTime(tripDTO.getArrivalTime());

    trip.setDepartureUtcZone(tripDTO.getDepartureUtcZone());
    trip.setArrivalUtcZone(tripDTO.getArrivalUtcZone());

    trip.setCabinClass(tripDTO.getCabinClass());

    this.tripRepository.save(trip);
  }

  /**
   * Fetch all trips associated to a user
   * @param pageable
   * @param userId
   * @return
   */
  public Page<TripDTO> getAllTripsFromUser(Pageable pageable, String userId) {
    return tripRepository.findAllByUserId(pageable, userId).map(TripDTO::new);
  }
}
