import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Upload } from '../models/upload.model';
import { UploadService } from '../services/upload.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  form!: FormGroup;

  private fileToUpload: File | null = null;

  constructor(private formBuilder: FormBuilder, private uploadService: UploadService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      comments: [''],
      file: ['', Validators.required]
    });
  }

  onFileChange(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
      this.fileToUpload = target.files[0];
    }
  }
  
  onSubmit() {
    if (this.form.invalid || !this.fileToUpload) {
      return;
    }
  
    const formInput = this.form.value as Upload;
    formInput.file = this.fileToUpload;
  
    this.uploadService.upload(formInput).subscribe({
      next: response => {
        console.log('Upload successful', response);
        // Assuming the response is an object with a bundleId property
        this.router.navigate(['display', response.bundleId]);
      },
      error: error => {
        console.log('Upload failed', error);
        alert(`Upload failed: ${error.message}`);
      },
    });
  }  

  onCancel() {
    console.log('Form cancelled');
    // remember to replace this with the actual function to reset the form or navigate to another view
  }
}


