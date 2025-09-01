import { Routes } from '@angular/router';
import { Register } from './components/customer/register/register/register';
import { Login } from './components/customer/login/login';
import { Menu } from './components/customer/menu/menu';
import { Dashboard } from './components/customer/dashboard/dashboard';
import { LoanApplication } from './components/customer/loan-application/loan-application';

export const routes: Routes = [
    {path:'',component:Menu},
    {path:'customer', component:Register},
    {path:'login', component:Login},
    {path:'dashboard', component:Dashboard},
    {path:'apply-for-loan', component:LoanApplication},

    {path:'admin', component:Login}
];