import { Route } from '@angular/router';
import { AddTripFormComponent } from 'app/add-trip-form/add-trip-form.component';

export const addTripFormRoute: Route = {
  path: '',
  component: AddTripFormComponent,
  outlet: 'addtripform',
};
