import { Component, OnDestroy, OnInit } from '@angular/core';
import { AddTripService } from 'app/add-trip-form/add-trip.service';
import { AccountService } from 'app/core/auth/account.service';

import { LoginModalService } from 'app/core/login/login-modal.service';
import { Account } from 'app/core/user/account.model';
import { Trip } from 'app/shared/models/trip.model';
import { SidenavService } from 'app/shared/services/sidenav.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  tripsSubscription?: Subscription;
  trips: Trip[] | null = null;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private sidenavService: SidenavService,
    public addTripService: AddTripService
  ) {}

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
    this.tripsSubscription = this.addTripService.getAllTripsFromUser();
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginModalService.open();
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
    if (this.tripsSubscription) {
      this.tripsSubscription.unsubscribe();
    }
  }

  addNewTrip(): void {
    this.sidenavService.openSidenav();
  }
}
