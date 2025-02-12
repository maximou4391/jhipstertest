import { NgModule } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { JhipstertestSharedModule } from 'app/shared/shared.module';
import { JhipstertestCoreModule } from 'app/core/core.module';
import { JhipstertestAppRoutingModule } from './app-routing.module';
import { JhipstertestHomeModule } from './home/home.module';
import { JhipstertestEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { AddTripFormComponent } from './add-trip-form/add-trip-form.component';

@NgModule({
  imports: [
    BrowserModule,
    JhipstertestSharedModule,
    JhipstertestCoreModule,
    JhipstertestHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    JhipstertestEntityModule,
    JhipstertestAppRoutingModule,
    MatSidenavModule,
  ],
  declarations: [
    MainComponent,
    NavbarComponent,
    ErrorComponent,
    PageRibbonComponent,
    ActiveMenuDirective,
    FooterComponent,
    AddTripFormComponent,
  ],
  bootstrap: [MainComponent],
})
export class JhipstertestAppModule {}
