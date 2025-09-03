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
export class AdminLogin {
  constructor(private http: HttpClient, private router: Router) {}

 onSubmit(formValue: { email: string; password: string }) {
  this.http.post<{ token: string }>(
    'http://localhost:8081/api/v1/customers/login',
    { email: formValue.email, password: formValue.password } // <-- send as object
  ).subscribe({
     next: (response) => {
        // Store token in localStorage
        localStorage.setItem('token', response.token);
        console.log('Login successful, token saved.', response.token);
      // Redirect to /customer
      this.router.navigate(['/view-applications']);
    },
    error: (err) => {
      console.error('❌ Login failed:', err);
      alert('Invalid credentials or server error');
    }
  });
}
}