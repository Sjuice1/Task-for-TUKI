import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { DropDownMenuDirective } from './header/dropdown-menu-directive';
import { ImagesModule } from './images/images.module';
import { StoreModule } from '@ngrx/store';
import { imageReducer } from './store/image.reducer';
import { ImageEffects } from './store/image.effects';
import { EffectsModule } from '@ngrx/effects';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    DropDownMenuDirective,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ImagesModule,
    HttpClientModule,
    StoreModule.forRoot({
      images : imageReducer
    }),
    EffectsModule.forRoot([ImageEffects]),
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
