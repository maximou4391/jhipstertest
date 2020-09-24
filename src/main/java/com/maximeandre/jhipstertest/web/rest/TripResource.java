package com.maximeandre.jhipstertest.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maximeandre.jhipstertest.domain.User;
import com.maximeandre.jhipstertest.security.AuthoritiesConstants;
import com.maximeandre.jhipstertest.service.TripService;
import com.maximeandre.jhipstertest.service.UserService;
import com.maximeandre.jhipstertest.service.dto.TripDTO;

import io.github.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class TripResource {

  private final Logger log = LoggerFactory.getLogger(TripResource.class);

  private final TripService tripService;

  private final UserService userService;

  public TripResource(TripService tripService, UserService userService) {
    this.tripService = tripService;
    this.userService = userService;
  }

  @PostMapping("/addtrip")
  @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
  public void createTrip(@RequestBody TripDTO tripDTO) {
    tripService.createTrip(tripDTO, getConnectedUserId());
  }

  @GetMapping("/trips")
  @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
  public ResponseEntity<List<TripDTO>> getAllTripsFromConnectedUser(Pageable pageable) {
    final Page<TripDTO> page = tripService.getAllTripsFromUser(pageable, getConnectedUserId());
    HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * Get the current connected user id
   *
   * @return the current connected user id
   */
  private String getConnectedUserId() {
    final Optional<User> isUser = userService.getUserWithAuthorities();
    if (!isUser.isPresent()) {
      // Should not happen as we will not shown the + button if user is not logged in
      log.error("User is not logged in");
    }
    final User user = isUser.get();
    return user.getId();
  }

}
