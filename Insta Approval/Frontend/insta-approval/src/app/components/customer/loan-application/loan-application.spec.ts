import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoanApplication } from './loan-application';

describe('LoanApplication', () => {
  let component: LoanApplication;
  let fixture: ComponentFixture<LoanApplication>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanApplication],
      imports: [ReactiveFormsModule, HttpClientTestingModule]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanApplication);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    expect(component.loanForm.valid).toBeFalsy();
  });

  it('should enable submit when form valid', () => {
    component.loanForm.controls['customerId'].setValue('1');
    component.loanForm.controls['loanTypeId'].setValue('1');
    component.loanForm.controls['loanAmount'].setValue(5000);
    expect(component.loanForm.valid).toBeTruthy();
  });
});
