import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {

  isEdit = false;
  errors: Record<string, string> = {};

  customer: any = {
    id: null,
    firstName: '',
    lastName: '',
    email: '',
    mobile: '',
    age: null,
    gender: '',
    address1: '',
    address2: '',
    dateOfBirth: ''
  };

  constructor(
    private service: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.service.getCustomerById(+id).subscribe(res => {
        this.customer = res;
      });
    }
  }

  submit(): void {
    this.errors = {};

    const request$ = this.isEdit
      ? this.service.updateCustomer(this.customer)
      : this.service.createCustomer(this.customer);

    request$.subscribe({
      next: () => this.router.navigate(['/customers']),
      error: (err) => {
        if (err.error?.fieldErrors) {
          this.errors = err.error.fieldErrors;
        } else {
          const message = err.error?.message || '';
          if (message.toLowerCase().includes('email')) {
            this.errors['email'] = message;
          }
          if (message.toLowerCase().includes('mobile')) {
            this.errors['mobile'] = message;
          }
        }
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/customers']);
  }
}