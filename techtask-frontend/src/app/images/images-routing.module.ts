import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ImagesComponent } from './images.component';
import { imagesResolver } from './images.resolver';

const routes: Routes = [
  {path:"", component:ImagesComponent,resolve:[imagesResolver]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ImagesRoutingModule { }
