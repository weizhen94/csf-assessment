import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DisplayService } from '../services/display.service';
import { DataService } from '../services/data.service';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {
  bundle: any;

  constructor(private route: ActivatedRoute, private router: Router, private displayService: DisplayService, private dataService: DataService) { }

  ngOnInit(): void {
    const bundleId = this.route.snapshot.paramMap.get('bundleId');
    if (bundleId) {
      this.displayService.getBundle(bundleId).subscribe({
        next: bundle => this.bundle = bundle,
        error: error => console.error('Failed to fetch bundle', error)
      });
    }
  }

  onBack() {
    this.dataService.changeBundle(this.bundle);
    this.router.navigate(['/landing']);
  }
}
