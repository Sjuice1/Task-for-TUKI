import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { ImageState } from '../../store/image.reducer';

@Component({
  selector: 'app-image-list',
  templateUrl: './image-list.component.html',
  styleUrl: './image-list.component.scss'
})
export class ImageListComponent implements OnInit{
  images! : string[];
  isLoading! : boolean;

  constructor(private store : Store<{images : ImageState}> ){}

  ngOnInit(): void {
    this.store.select("images")
        .subscribe(state => {
          this.images = state.images;
          this.isLoading = state.loading;
        })
  }
}
