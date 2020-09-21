import { Injectable } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Injectable({ providedIn: 'root' })
export class SidenavService {
  private sidenav!: MatSidenav;

  constructor() {}

  public setSidenav(sidenav: MatSidenav): void {
    this.sidenav = sidenav;
  }

  public openSidenav(): void {
    this.sidenav.open();
  }

  public closeSidenav(): void {
    this.sidenav.close();
  }
}
