import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  form!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', Validators.required],
      title: ['', Validators.required],
      comments: [''],
      file: ['', Validators.required]
    });
  }

  onSubmit() {
    console.log('Form submitted', this.form.value);
    // You will replace this with the actual function to send data to the backend
  }

  onCancel() {
    console.log('Form cancelled');
    // You will replace this with the actual function to reset the form or navigate to another view
  }
}

