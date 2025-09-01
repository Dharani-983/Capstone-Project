import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class Dashboard {

  constructor(private router: Router) {}

  goToApplyForLoan(): void {
    this.router.navigate(['/apply-for-loan']);
  }

  goToTrackingStatus(): void {
    this.router.navigate(['/loan-tracking']);
  }
}
