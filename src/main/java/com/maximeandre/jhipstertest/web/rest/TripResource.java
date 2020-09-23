package com.maximeandre.jhipstertest.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.maximeandre.jhipstertest.service.dto.UserDTO;

import io.github.jhipster.web.util.PaginationUtil;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link User} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api")
public class TripResource {

  private final Logger log = LoggerFactory.getLogger(TripResource.class);

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TripService tripService;

  private final UserService userService;

  public TripResource(TripService tripService, UserService userService) {
    this.tripService = tripService;
    this.userService = userService;
  }

  @PostMapping("/addtrip")
  @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
  public void createTrip(@RequestBody TripDTO tripDTO) {
    final Optional<User> isUser = userService.getUserWithAuthorities();
    if(!isUser.isPresent()) {
      // Should not happen as we will not shown the + button if user is not logged in
      log.error("User is not logged in");
    }
    final User user = isUser.get();
    String userId = user.getId();
    tripService.createTrip(tripDTO, userId);
  }

  @GetMapping("/trips")
  public ResponseEntity<List<TripDTO>> getAllTripsFromConnectedUser(Pageable pageable) {
    final Optional<User> isUser = userService.getUserWithAuthorities();
    if(!isUser.isPresent()) {
      // Should not happen as we will not shown the + button if user is not logged in
      log.error("User is not logged in");
    }
    final User user = isUser.get();
    String userId = user.getId();
    final Page<TripDTO> page = tripService.getAllTripsFromUser(pageable, userId);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

}
