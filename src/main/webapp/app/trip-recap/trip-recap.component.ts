import { Component, Input, OnInit } from '@angular/core';
import { Trip } from 'app/shared/models/trip.model';
import * as moment from 'moment-timezone';

@Component({
  selector: 'jhi-trip-recap',
  templateUrl: './trip-recap.component.html',
  styleUrls: ['./trip-recap.component.scss'],
})
export class TripRecapComponent implements OnInit {
  @Input() trip!: Trip;

  departureDateAndTime!: moment.Moment;

  constructor() {}

  ngOnInit(): void {
    // IMPROVEMENT display in UTC? Why?
    this.departureDateAndTime = moment.tz(this.trip.departureDate + ' ' + this.trip.departureTime, this.trip.departureUtcZone);
  }
}
