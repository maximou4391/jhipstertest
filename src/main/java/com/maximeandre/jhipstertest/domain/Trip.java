package com.maximeandre.jhipstertest.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

/**
 * A trip.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_trip")
public class Trip extends AbstractAuditingEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @NotNull
  private String userId;

  @NotBlank
  @Size(min = 3, max = 3)
  private String departurePlace;

  @NotNull
  private String departureDate;

  @NotNull
  private String departureTime;

  @NotNull
  private String departureUtcZone;

  @NotBlank
  @Size(min = 3, max = 3)
  private String arrivalPlace;

  @NotNull
  private String arrivalDate;

  @NotNull
  private String arrivalTime;

  @NotNull
  private String arrivalUtcZone;

  @NotNull
  private String cabinClass;

  private String cabinCategory;

  private String marketingFlightIdentifier;

  private String operatingFlightIdentifier;

  private String marketingAirline;

  private String operatingAirline;

  private String transportation;

  private String bookingClass;

  public Trip(String id, String userId, @NotBlank @Size(min = 3, max = 3) String departurePlace,
          @NotNull String departureDate, @NotNull String departureTime, @NotNull String departureUtcZone,
          @NotBlank @Size(min = 3, max = 3) String arrivalPlace, @NotNull String arrivalDate,
          @NotNull String arrivalTime, @NotNull String arrivalUtcZone, @NotNull String cabinClass,
          @NotNull String cabinCategory, @NotNull String marketingFlightIdentifier,
          @NotNull String operatingFlightIdentifier, @NotNull String marketingAirline, @NotNull String operatingAirline,
          @NotNull String transportation, @NotNull String bookingClass) {
    this.id = id;
    this.userId = userId;
    this.departurePlace = departurePlace;
    this.departureDate = departureDate;
    this.departureTime = departureTime;
    this.departureUtcZone = departureUtcZone;
    this.arrivalPlace = arrivalPlace;
    this.arrivalDate = arrivalDate;
    this.arrivalTime = arrivalTime;
    this.arrivalUtcZone = arrivalUtcZone;
    this.cabinClass = cabinClass;
    this.cabinCategory = cabinCategory;
    this.marketingFlightIdentifier = marketingFlightIdentifier;
    this.operatingFlightIdentifier = operatingFlightIdentifier;
    this.marketingAirline = marketingAirline;
    this.operatingAirline = operatingAirline;
    this.transportation = transportation;
    this.bookingClass = bookingClass;
  }

  public Trip() {
    // Empty constructor needed for Jackson.
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDeparturePlace() {
    return departurePlace;
  }

  public void setDeparturePlace(String departurePlace) {
    this.departurePlace = departurePlace;
  }

  public String getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(String departureDate) {
    this.departureDate = departureDate;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getDepartureUtcZone() {
    return departureUtcZone;
  }

  public void setDepartureUtcZone(String departureUtcZone) {
    this.departureUtcZone = departureUtcZone;
  }

  public String getArrivalPlace() {
    return arrivalPlace;
  }

  public void setArrivalPlace(String arrivalPlace) {
    this.arrivalPlace = arrivalPlace;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public void setArrivalDate(String arrivalDate) {
    this.arrivalDate = arrivalDate;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public String getArrivalUtcZone() {
    return arrivalUtcZone;
  }

  public void setArrivalUtcZone(String arrivalUtcZone) {
    this.arrivalUtcZone = arrivalUtcZone;
  }

  public String getCabinClass() {
    return cabinClass;
  }

  public void setCabinClass(String cabinClass) {
    this.cabinClass = cabinClass;
  }

  public String getCabinCategory() {
    return cabinCategory;
  }

  public void setCabinCategory(String cabinCategory) {
    this.cabinCategory = cabinCategory;
  }

  public String getMarketingFlightIdentifier() {
    return marketingFlightIdentifier;
  }

  public void setMarketingFlightIdentifier(String marketingFlightIdentifier) {
    this.marketingFlightIdentifier = marketingFlightIdentifier;
  }

  public String getOperatingFlightIdentifier() {
    return operatingFlightIdentifier;
  }

  public void setOperatingFlightIdentifier(String operatingFlightIdentifier) {
    this.operatingFlightIdentifier = operatingFlightIdentifier;
  }

  public String getMarketingAirline() {
    return marketingAirline;
  }

  public void setMarketingAirline(String marketingAirline) {
    this.marketingAirline = marketingAirline;
  }

  public String getOperatingAirline() {
    return operatingAirline;
  }

  public void setOperatingAirline(String operatingAirline) {
    this.operatingAirline = operatingAirline;
  }

  public String getTransportation() {
    return transportation;
  }

  public void setTransportation(String transportation) {
    this.transportation = transportation;
  }

  public String getBookingClass() {
    return bookingClass;
  }

  public void setBookingClass(String bookingClass) {
    this.bookingClass = bookingClass;
  }

  @Override
  public String toString() {
    return "TripDTO{" + "id='" + id + '\'' + ", departurePlace='" + departurePlace + '\'' + ", departureDate='"
            + departureDate + '\'' + ", departureTime='" + departureTime + '\'' + ", departureUtcZone='"
            + departureUtcZone + '\'' + ", arrivalPlace='" + arrivalPlace + '\'' + ", arrivalDate='" + arrivalDate
            + '\'' + ", arrivalTime='" + arrivalTime + '\'' + ", arrivalUtcZone='" + arrivalUtcZone + '\''
            + ", cabinClass='" + cabinClass + '\'' + ", cabinCategory='" + cabinCategory + '\''
            + ", marketingFlightIdentifier='" + marketingFlightIdentifier + '\'' + ", operatingFlightIdentifier='"
            + operatingFlightIdentifier + '\'' + ", marketingAirline='" + marketingAirline + '\''
            + ", operatingAirline='" + operatingAirline + '\'' + ", transportation='" + transportation + '\''
            + ", bookingClass='" + bookingClass + '\'' + '}';
  }
}
