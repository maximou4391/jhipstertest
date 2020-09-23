import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AddTripService } from 'app/add-trip-form/add-trip.service';
import { Cabin } from 'app/shared/enums/cabins.enum';
import { Transportation } from 'app/shared/enums/transportation.enum';
import { ITrip } from 'app/shared/models/trip.model';
import { SidenavService } from 'app/shared/services/sidenav.service';
import * as moment from 'moment-timezone';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-add-trip-form',
  templateUrl: './add-trip-form.component.html',
  styleUrls: ['./add-trip-form.component.scss'],
})
export class AddTripFormComponent implements OnInit {
  // Transportation dropdown data
  transportations = Transportation;
  transportationsKeys!: string[];

  // Cabin class dropdown data
  cabins = Cabin;
  cabinsKeys!: string[];

  utcZones!: string[];
  guessedUtcZone!: string;

  addTripForm!: FormGroup;

  addTripSubscription?: Subscription;

  constructor(private sidenavService: SidenavService, private formBuilder: FormBuilder, private addTripService: AddTripService) {}

  ngOnInit(): void {
    this.transportationsKeys = Object.keys(this.transportations);
    this.cabinsKeys = Object.keys(this.cabins);

    this.utcZones = moment.tz.names();

    this.addTripForm = this.formBuilder.group({
      departurePlace: '',
      departureDate: '',
      departureTime: '',
      departureUtcZone: '',
      arrivalPlace: '',
      arrivalDate: '',
      arrivalTime: '',
      arrivalUtcZone: '',
      cabinCategory: '',
      marketingFlightIdentifier: '',
      operatingFlightIdentifier: '',
      marketingAirline: '',
      operatingAirline: '',
      transportation: '',
      bookingClass: '',
      cabinClass: '',
    });

    this.initializeFormWithDefaultValues();
  }

  /**
   * Initialise some required inputs with default values to speed up dev ^^
   */
  initializeFormWithDefaultValues(): void {
    // Will be guessed on browser location, will be accurate for departure maybe but could be improved, for example
    // rather based on the airport code
    const guessedUtcZone: string = moment.tz.guess();
    if (guessedUtcZone) {
      this.addTripForm.get('departureUtcZone')?.setValue(guessedUtcZone);
      this.addTripForm.get('arrivalUtcZone')?.setValue(guessedUtcZone);
    }
    this.addTripForm.get('departureDate')?.setValue(moment().format('YYYY-MM-DD'));
    this.addTripForm.get('departureTime')?.setValue('12:00');
    this.addTripForm.get('arrivalDate')?.setValue(moment().add(1, 'days').format('YYYY-MM-DD'));
    this.addTripForm.get('arrivalTime')?.setValue('12:00');
  }

  cancelAddTripFormSubmission(): void {
    // TODO Might need to reset the form as well? Maybe they want what they previously typed?
    this.sidenavService.closeSidenav();
  }

  onAddTripFormSubmit(addTripFormData: ITrip): void {
    // Once trip is added in DB retrieve all the user trips to update UI
    this.addTripService.addTrip(addTripFormData).subscribe(() => this.addTripService.getAllTripsFromUser());

    this.sidenavService.closeSidenav();
    // Empty addTripForm form
    this.addTripForm.reset();
    this.initializeFormWithDefaultValues();
  }
}
