import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private bundleSource = new BehaviorSubject<any>({});
  currentBundle = this.bundleSource.asObservable();

  constructor() { }

  changeBundle(bundle: any) {
    this.bundleSource.next(bundle);
  }
}
