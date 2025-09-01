import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from "@angular/material/card";
import { MatInputModule } from "@angular/material/input";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatButtonModule } from "@angular/material/button";
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-registration',
  standalone: true,
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
  imports: [
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    ReactiveFormsModule
  ]
})
export class Register implements OnInit {

  registrationForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private http: HttpClient // inject HttpClient
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.maxLength(10)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      address: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      cibilScore: ['', Validators.required]
    });
  }

  goToLogin() {
    this.router.navigate(['/login']);
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      console.log('Form value before POST:', this.registrationForm.value);
      // call backend API
      this.http.post('http://localhost:8081/api/v1/customers/register', this.registrationForm.value)
        .subscribe({
          next: (res) => {
            console.log('✅ Registration successful', res);
            alert('Registration Successful!');
            this.router.navigate(['/login']); // redirect to login page
          },
          error: (err) => {
            console.error('❌ Registration failed', err);
            alert('Registration Failed! Please try again.');
          }
        });
    }
  }
}
