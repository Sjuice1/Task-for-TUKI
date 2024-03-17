import { NgModule } from "@angular/core";
import { ImagesComponent } from "./images.component";
import { ImageListComponent } from "./image-list/image-list.component";
import { CommonModule } from "@angular/common";
import { ImageItemComponent } from "./image-list/image-item/image-item.component";
import { RouterModule } from "@angular/router";
import { ImagesRoutingModule } from "./images-routing.module";
import { LoadingComponent } from "../loading/loading.component";

@NgModule({
    declarations: [
      ImagesComponent,
      ImageListComponent,
      ImageItemComponent,
      LoadingComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        ImagesRoutingModule
    ]
  })
  export class ImagesModule { }
  