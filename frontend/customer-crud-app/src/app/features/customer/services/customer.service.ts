import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';
import { PageResponse } from '../models/page-response.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private readonly API_URL = 'http://localhost:8080/api/customer';

  constructor(private http: HttpClient) {}

  getCustomers(
    pageNo: number,
    pageSize: number,
    sortBy: string,
    sortDir: 'asc' | 'desc',
    search?: string
  ): Observable<PageResponse<Customer>> {
    let params = new HttpParams()
      .set('pageNo', pageNo)
      .set('pageSize', pageSize)
      .set('sortBy', sortBy)
      .set('sortDir', sortDir);

    if (search && search.trim() !== '') {
      params = params.set('search', search.trim());
    }

    return this.http.get<PageResponse<Customer>>(this.API_URL, { params });
  }

  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }

  createCustomer(data: any) {
    return this.http.post(this.API_URL, data);
  }

  updateCustomer(data: any) {
    return this.http.put(`${this.API_URL}`, data);
  }

  getCustomerById(id: number) {
    return this.http.get(`${this.API_URL}/${id}`);
  }
}
