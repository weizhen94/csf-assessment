import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisplayService {

  private apiUrl = 'http://localhost:8080/api/bundle/';

  constructor(private http: HttpClient) { }

  getBundle(bundleId: string): Observable<any> {
    return this.http.get<any>(this.apiUrl + bundleId);
  }

}
