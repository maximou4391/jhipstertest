import { Component, OnInit } from '@angular/core';
import { SidenavService } from 'app/shared/services/sidenav.service';

@Component({
  selector: 'jhi-add-trip-form',
  templateUrl: './add-trip-form.component.html',
  styleUrls: ['./add-trip-form.component.scss'],
})
export class AddTripFormComponent implements OnInit {
  constructor(private sidenavService: SidenavService) {}

  ngOnInit(): void {}

  cancelAddTripFormSubmission(): void {
    // TODO Might need to clean the form as well?
    this.sidenavService.closeSidenav();
  }

  confirmAddTripFormSubmission(): void {
    // TODO call service to add trip in DB
    this.sidenavService.closeSidenav();
  }
}
