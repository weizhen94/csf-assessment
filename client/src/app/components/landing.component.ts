import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent implements OnInit {
  bundles: any[] = [];

  constructor(private http: HttpClient, private router: Router, private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.currentBundle.subscribe(bundle => this.bundles.unshift(bundle));
    
    this.http.get<any[]>('/api/bundles').subscribe(data => {
      this.bundles = data;
    });
  }

  goToUpload(): void {
    this.router.navigate(['/']);
  }

  goToDisplay(bundleId: string): void {
    this.router.navigate(['/display', bundleId]);
  }
}

