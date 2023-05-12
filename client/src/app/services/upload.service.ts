import { Injectable } from '@angular/core';
import { Upload } from '../models/upload.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private uploadUrl = 'http://localhost:8080/api/upload'; 

  constructor(private http: HttpClient) { }

  upload(data: Upload): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('name', data.name);
    formData.append('title', data.title);
    formData.append('comments', data.comments);
    
    if (data.file) {
      formData.append('file', data.file);
    }
  
    return this.http.post(this.uploadUrl, formData);
  }
  
}

