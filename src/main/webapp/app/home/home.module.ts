import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { JhipstertestSharedModule } from 'app/shared/shared.module';
import { TripRecapComponent } from 'app/trip-recap/trip-recap.component';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [
    JhipstertestSharedModule,
    RouterModule.forChild([HOME_ROUTE]),
    MatCardModule,
    MatDividerModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    BrowserAnimationsModule,
  ],
  declarations: [HomeComponent, TripRecapComponent],
  exports: [],
})
export class JhipstertestHomeModule {}
