import { Component, AfterViewInit, ElementRef, ViewChild, OnDestroy } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { AddTripService } from 'app/add-trip-form/add-trip.service';

import { LoginService } from 'app/core/login/login.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'jhi-login-modal',
  templateUrl: './login.component.html',
})
export class LoginModalComponent implements AfterViewInit, OnDestroy {
  @ViewChild('username', { static: false })
  username?: ElementRef;

  authenticationError = false;
  tripsSubscription?: Subscription;
  loginForm = this.fb.group({
    username: [''],
    password: [''],
    rememberMe: [false],
  });

  constructor(
    private loginService: LoginService,
    private router: Router,
    public activeModal: NgbActiveModal,
    private fb: FormBuilder,
    private addTripService: AddTripService
  ) {}

  ngAfterViewInit(): void {
    if (this.username) {
      this.username.nativeElement.focus();
    }
  }

  cancel(): void {
    this.authenticationError = false;
    this.loginForm.patchValue({
      username: '',
      password: '',
    });
    this.activeModal.dismiss('cancel');
  }

  login(): void {
    this.loginService
      .login({
        username: this.loginForm.get('username')!.value,
        password: this.loginForm.get('password')!.value,
        rememberMe: this.loginForm.get('rememberMe')!.value,
      })
      .subscribe(
        () => {
          this.authenticationError = false;
          this.activeModal.close();
          if (
            this.router.url === '/account/register' ||
            this.router.url.startsWith('/account/activate') ||
            this.router.url.startsWith('/account/reset/')
          ) {
            this.router.navigate(['']);
          }
          this.tripsSubscription = this.addTripService.getAllTripsFromUser();
        },
        () => (this.authenticationError = true)
      );
  }

  register(): void {
    this.activeModal.dismiss('to state register');
    this.router.navigate(['/account/register']);
  }

  requestResetPassword(): void {
    this.activeModal.dismiss('to state requestReset');
    this.router.navigate(['/account/reset', 'request']);
  }

  ngOnDestroy(): void {
    if (this.tripsSubscription != null) {
      this.tripsSubscription.unsubscribe();
    }
  }
}
