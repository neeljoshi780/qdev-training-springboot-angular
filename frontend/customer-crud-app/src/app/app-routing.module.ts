import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CustomerListComponent } from './features/customer/components/customer-list/customer-list.component';
import { CustomerFormComponent } from './features/customer/components/customer-form/customer-form.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'customers', component: CustomerListComponent },
  { path: 'customers/add', component: CustomerFormComponent },
  { path: 'customers/edit/:id', component: CustomerFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }