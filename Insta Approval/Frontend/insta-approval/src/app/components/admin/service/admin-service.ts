import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AdminService {

  private baseUrl = 'http://localhost:8082/admin/loans';
  private token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkaGFyYW5pbWFydGhhbGFAZ21haWwuY29tIiwidXNlcklkIjoxLCJyb2xlcyI6WyJBRE1JTiJdLCJpYXQiOjE3NTY3OTAzMjgsImV4cCI6MTc1Njg3NjcyOH0.myvSg-W2SF6cgHW2KtinLSL1JYzuZxrTRQ5STnbUiPw'; // replace with real token from login

  constructor(private http: HttpClient) {}

  approveLoan(loanId: number, remarks: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${this.token}` });
    const body = { remarks }; // matches AdminActionDTO
    return this.http.post(`${this.baseUrl}/${loanId}/approve`, body, { headers });
  }

  rejectLoan(loanId: number, remarks: string): Observable<any> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${this.token}` });
    const body = { remarks };
    return this.http.post(`${this.baseUrl}/${loanId}/reject`, body, { headers });
  }

  getPendingLoans(): Observable<any[]> {
    const headers = new HttpHeaders({ Authorization: `Bearer ${this.token}` });
    return this.http.get<any[]>(`${this.baseUrl}/pending`, { headers });
  }
}
