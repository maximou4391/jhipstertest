import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';

import { JhipstertestSharedModule } from 'app/shared/shared.module';
import { TripRecapComponent } from 'app/trip-recap/trip-recap.component';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [JhipstertestSharedModule, RouterModule.forChild([HOME_ROUTE]), MatCardModule, MatDividerModule, MatButtonModule, MatIconModule],
  declarations: [HomeComponent, TripRecapComponent],
  exports: [],
})
export class JhipstertestHomeModule {}
