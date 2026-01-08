import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private API = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  login(data: { username: string; password: string }) {
    return this.http.post<any>(`${this.API}/signin`, data)
      .pipe(
        tap(res => {
          localStorage.setItem('token', res.token);
        })
      );
  }

  logout() {
    localStorage.clear();
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}