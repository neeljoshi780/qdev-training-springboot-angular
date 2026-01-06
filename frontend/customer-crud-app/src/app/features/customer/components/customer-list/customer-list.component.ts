import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../models/customer.model';
import { PageResponse } from '../../models/page-response.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css'],
})
export class CustomerListComponent implements OnInit {
  pageResponse!: PageResponse<Customer>;

  searchText: string = '';
  pageSize = 10;
  sortBy: string = 'id';
  sortDir: 'asc' | 'desc' = 'asc';

  showDeleteModal = false;
  deleteCustomerId!: number;

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPage(0);
  }

  loadPage(pageNumber: number): void {
    this.customerService
      .getCustomers(
        pageNumber,
        this.pageSize,
        this.sortBy,
        this.sortDir,
        this.searchText
      )
      .subscribe((response) => {
        this.pageResponse = response;
      });
  }

  onSearch(): void {
    this.loadPage(0);
  }

  getSortIcon(column: string): string {
    return this.sortDir === 'asc' ? '▲' : '▼';
  }

  sort(column: string): void {
    if (this.sortBy === column) {
      this.sortDir = this.sortDir === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortBy = column;
      this.sortDir = 'asc';
    }
    this.loadPage(0);
  }

  onPageSizeChange(): void {
    this.loadPage(0);
  }

  openDeleteModal(id: number): void {
    this.deleteCustomerId = id;
    this.showDeleteModal = true;
  }

  closeDeleteModal(): void {
    this.showDeleteModal = false;
  }

  confirmDelete(): void {
    this.customerService.deleteCustomer(this.deleteCustomerId).subscribe(() => {
      this.closeDeleteModal();

      const currentPage =
        this.pageResponse.pageNumber > 0 &&
        this.pageResponse.content.length === 1
          ? this.pageResponse.pageNumber - 1
          : this.pageResponse.pageNumber;

      this.loadPage(currentPage);
    });
  }

  onEdit(id: number): void {
    this.router.navigate(['/customers/edit', id]);
  }

  onAdd(): void {
    this.router.navigate(['/customers/add']);
  }
}
