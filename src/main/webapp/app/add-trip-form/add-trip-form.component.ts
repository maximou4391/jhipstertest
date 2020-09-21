import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Cabin } from 'app/shared/enums/cabins.enum';
import { Transportation } from 'app/shared/enums/transportation.enum';
import { SidenavService } from 'app/shared/services/sidenav.service';
import * as moment from 'moment-timezone';

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

  constructor(private sidenavService: SidenavService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.transportationsKeys = Object.keys(this.transportations);
    this.cabinsKeys = Object.keys(this.cabins);

    this.utcZones = moment.tz.names();
    // Will be guessed on browser location, will be accurate for departure maybe but could be improved, for example
    // rather based on the airport code
    this.guessedUtcZone = moment.tz.guess();

    this.addTripForm = this.formBuilder.group({
      // Initialise some required inputs with default values to speed up dev ^^
      departurePlace: new FormControl(''),
      departureDate: new FormControl(moment().format('YYYY-MM-DD')),
      departureTime: new FormControl('12:00'),
      departureUtcZone: new FormControl(''),
      arrivalPlace: new FormControl(''),
      arrivalDate: new FormControl(moment().add(1, 'days').format('YYYY-MM-DD')),
      arrivalTime: new FormControl('12:00'),
      arrivalUtcZone: new FormControl(''),
      cabinCategory: new FormControl(''),
      marketingFlightIdentifier: new FormControl(''),
      operatingFlightIdentifier: new FormControl(''),
      marketingAirline: new FormControl(''),
      operatingAirline: new FormControl(''),
      transportation: new FormControl(''),
      bookingClass: new FormControl(''),
      cabinClass: new FormControl(''),
    });

    if (this.guessedUtcZone) {
      this.addTripForm.controls['departureUtcZone'].setValue(this.guessedUtcZone);
      this.addTripForm.controls['arrivalUtcZone'].setValue(this.guessedUtcZone);
    }
  }

  cancelAddTripFormSubmission(): void {
    // TODO Might need to reset the form as well? Maybe they want what they previously typed?
    this.sidenavService.closeSidenav();
  }

  onAddTripFormSubmit(addTripFormData: any): void {
    // TODO call service to add trip in DB
    this.sidenavService.closeSidenav();

    // Empty form
    this.addTripForm.reset();
    console.warn(addTripFormData);
  }
}
