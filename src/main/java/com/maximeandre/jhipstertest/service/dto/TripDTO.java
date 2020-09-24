package com.maximeandre.jhipstertest.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.maximeandre.jhipstertest.domain.Trip;

/**
 * A DTO(Data Transfer Object) representing a trip.
 */
public class TripDTO {

  @NotBlank
  @Size(min = 3, max = 3)
  private String departurePlace;

  @NotBlank
  private String departureDate;

  @NotBlank
  private String departureTime;

  @NotBlank
  private String departureUtcZone;

  @NotBlank
  @Size(min = 3, max = 3)
  private String arrivalPlace;

  @NotBlank
  private String arrivalDate;

  @NotBlank
  private String arrivalTime;

  @NotBlank
  private String arrivalUtcZone;

  @NotBlank
  private String cabinClass;

  private String cabinCategory;

  private String marketingFlightIdentifier;

  private String operatingFlightIdentifier;

  private String marketingAirline;

  private String operatingAirline;

  private String transportation;

  private String bookingClass;

  public TripDTO() {
    // Empty constructor needed for Jackson.
  }

  public TripDTO(Trip trip) {
    this.departurePlace = trip.getDeparturePlace();
    this.departureDate = trip.getDepartureDate();
    this.departureTime = trip.getDepartureTime();
    this.departureUtcZone = trip.getDepartureUtcZone();
    this.arrivalPlace = trip.getArrivalPlace();
    this.arrivalDate = trip.getArrivalDate();
    this.arrivalTime = trip.getArrivalTime();
    this.arrivalUtcZone = trip.getArrivalUtcZone();
    this.cabinClass = trip.getCabinClass();
    this.cabinCategory = trip.getCabinCategory();
    this.marketingFlightIdentifier = trip.getMarketingFlightIdentifier();
    this.operatingFlightIdentifier = trip.getOperatingFlightIdentifier();
    this.marketingAirline = trip.getMarketingAirline();
    this.operatingAirline = trip.getOperatingAirline();
    this.transportation = trip.getTransportation();
    this.bookingClass = trip.getBookingClass();
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
    return "TripDTO{" + "departurePlace='" + departurePlace + '\'' + ", departureDate='" + departureDate + '\''
            + ", departureTime='" + departureTime + '\'' + ", departureUtcZone='" + departureUtcZone + '\''
            + ", arrivalPlace='" + arrivalPlace + '\'' + ", arrivalDate='" + arrivalDate + '\'' + ", arrivalTime='"
            + arrivalTime + '\'' + ", arrivalUtcZone='" + arrivalUtcZone + '\'' + ", cabinClass='" + cabinClass + '\''
            + ", cabinCategory='" + cabinCategory + '\'' + ", marketingFlightIdentifier='" + marketingFlightIdentifier
            + '\'' + ", operatingFlightIdentifier='" + operatingFlightIdentifier + '\'' + ", marketingAirline='"
            + marketingAirline + '\'' + ", operatingAirline='" + operatingAirline + '\'' + ", transportation='"
            + transportation + '\'' + ", bookingClass='" + bookingClass + '\'' + '}';
  }
}
