export interface ITrip {
  departurePlace: string;
  departureDate: Date;
  departureTime: string;
  departureUtcZone: string;
  arrivalPlace: string;
  arrivalDate: Date;
  arrivalTime: string;
  arrivalUtcZone: string;
  cabinClass: string;
  cabinCategory?: string;
  marketingFlightIdentifier?: string;
  operatingFlightIdentifier?: string;
  marketingAirline?: string;
  operatingAirline?: string;
  transportation?: string;
  bookingClass?: string;
}

export class Trip implements ITrip {
  constructor(
    public departurePlace: string,
    public departureDate: Date,
    public departureTime: string,
    public departureUtcZone: string,
    public arrivalPlace: string,
    public arrivalDate: Date,
    public arrivalTime: string,
    public arrivalUtcZone: string,
    public cabinClass: string,
    public cabinCategory?: string,
    public marketingFlightIdentifier?: string,
    public operatingFlightIdentifier?: string,
    public marketingAirline?: string,
    public operatingAirline?: string,
    public transportation?: string,
    public bookingClass?: string
  ) {}
}
