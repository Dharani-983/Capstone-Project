import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Loanservice {
  private apiUrl = 'http://localhost:8082/loans'; // ✅ API Gateway

  constructor(private http: HttpClient) {}

  // Helper: always use "jwt" for consistency
  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('jwt');
    return new HttpHeaders({
      'Authorization': token ? `Bearer ${token}` : ''
    });
  }

  // Apply for loan
  applyLoan(loanApplication: any): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/apply`,
      loanApplication,
      { headers: this.getAuthHeaders() }
    );
  }

  // Fetch all loan types
  getAllLoanTypes(): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.apiUrl}/types`,
      { headers: this.getAuthHeaders() }
    );
  }

  // Get loan status
  getLoanStatus(loanId: number): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/${loanId}/status`,
      { headers: this.getAuthHeaders() }
    );
  }
}