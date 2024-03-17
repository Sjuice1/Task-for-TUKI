import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"images", loadChildren: () => import("./images/images.module").then(i=>i.ImagesModule)},
  {path:"**", redirectTo:"images"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
