import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadComponent } from './components/upload.component';
import { DisplayComponent } from './components/display.component';
import { LandingComponent } from './components/landing.component';

const routes: Routes = [
  { path: '', component: UploadComponent },
  { path: 'display/:bundleId', component: DisplayComponent },
  { path: 'landing', component: LandingComponent },
  { path: '**', redirectTo: '', pathMatch:'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
