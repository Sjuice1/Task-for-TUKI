import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-image',
  templateUrl: './image-item.component.html',
  styleUrl: './image-item.component.scss'
})
export class ImageItemComponent implements OnInit {
  @Input('image') image! : String
  loaded! : boolean;

  ngOnInit(): void {
    this.loaded = true;
  }

  onError(){
    this.loaded = false;
  }
}
