import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // For *ngIf and *ngFor
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-loan-application',
  templateUrl: './loan-application.html',
  styleUrls: ['./loan-application.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class LoanApplication {
  loanForm!: FormGroup;
  loanTypes = [
    { id: 1, name: 'Interest-Free Car Loan' },
    { id: 2, name: 'Car Loan 0% First Year' }
  ];
  selectedFiles: { [key: string]: File[] } = {};
  statusMessage = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    // Initialize form
    this.loanForm = this.fb.group({
      name: [{ value: 'John Doe', disabled: true }],
      email: [{ value: 'john.doe@example.com', disabled: true }],
      employer: ['', Validators.required],
      income: ['', Validators.required],
      loanTypeId: ['', Validators.required],
      loanAmount: ['', [Validators.required, Validators.min(1000)]],
    });
  }

  onFileSelected(event: any, type: string) {
    const files: FileList = event.target.files;
    if (files && files.length > 0) {
      this.selectedFiles[type] = Array.from(files);
    }
  }

  onSubmit() {
    if (this.loanForm.invalid) {
      this.statusMessage = 'Please fill all required fields correctly.';
      return;
    }

    const token = localStorage.getItem('authToken') || '';
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    const payload = {
      customerId: 1, // assume logged in user
      loanTypeId: this.loanForm.getRawValue().loanTypeId, // getRawValue because name/email are disabled
      loanAmount: this.loanForm.getRawValue().loanAmount,
      remarks: 'New Loan Application'
    };

    // Step 1: Apply for loan
    this.http.post<any>('http://localhost:8082/loans/apply', payload, { headers })
      .subscribe({
        next: (res) => {
          const loanId = res.applicationId;
          this.uploadDocuments(loanId, headers);
        },
        error: () => this.statusMessage = 'Loan application failed!'
      });
  }

  uploadDocuments(loanId: number, headers: HttpHeaders) {
    if (!this.selectedFiles || Object.keys(this.selectedFiles).length === 0) {
      this.statusMessage = 'Loan application submitted (no documents uploaded).';
      return;
    }

    const formData = new FormData();
    const types: string[] = [];

    Object.keys(this.selectedFiles).forEach((type) => {
      this.selectedFiles[type].forEach((file) => {
        formData.append('files', file);
        types.push(type);
      });
    });

    formData.append('types', types.toString());

    this.http.post<any>(`http://localhost:8082/loans/${loanId}/documents`, formData, { headers })
      .subscribe({
        next: () => this.statusMessage = 'Loan application submitted successfully!',
        error: () => this.statusMessage = 'Document upload failed!'
      });
  }
}
