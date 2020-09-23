import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { SERVER_API_URL } from 'app/app.constants';
import { ITrip, Trip } from 'app/shared/models/trip.model';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AddTripService {
  // Readonly BehaviorSubject on which we will call next when we receive the trip list from the backend
  private readonly tripsSubject$: BehaviorSubject<Trip[]> = new BehaviorSubject<Trip[]>([]);

  // Observable accessible from other components
  public readonly trips$: Observable<Trip[]> = this.tripsSubject$.asObservable();

  constructor(private http: HttpClient) {}

  addTrip(trip: ITrip): Observable<ITrip> {
    return this.http.post<ITrip>(SERVER_API_URL + 'api/addtrip', trip);
  }

  // IMPROVEMENT We could have used a store to avoid doing subscription
  getAllTripsFromUser(): Subscription {
    return this.http
      .get<ITrip[]>(SERVER_API_URL + 'api/trips', { observe: 'response' })
      .subscribe(({ body }: HttpResponse<Trip[]>) => {
        if (body !== null) {
          this.tripsSubject$.next(body);
        }
      });
  }
}
