import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatCardModule } from "@angular/material/card";
import { FormsModule } from '@angular/forms';
import { MatInputModule } from "@angular/material/input";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.html',
  styleUrls: ['./login.css'],
  imports: [MatCardModule, FormsModule, MatInputModule, CommonModule]
})
export class Login {
  constructor(private http: HttpClient, private router: Router) {}

 onSubmit(formValue: { email: string; password: string }) {
  this.http.post<{ token: string }>(
    'http://localhost:8081/api/v1/customers/login',
    { email: formValue.email, password: formValue.password } // <-- send as object
  ).subscribe({
    next: (res) => {
      localStorage.setItem('jwt', res.token); // save token
      console.log('✅ Logged in, token =', res.token);

      // Redirect to /customer
      this.router.navigate(['/dashboard']);
    },
    error: (err) => {
      console.error('❌ Login failed:', err);
      alert('Invalid credentials or server error');
    }
  });
}
}