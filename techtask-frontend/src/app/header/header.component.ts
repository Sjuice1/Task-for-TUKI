import { Component, OnInit, effect } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { ImageState } from '../store/image.reducer';
import {
  START_FETCHING_IMAGES,
  START_FETCHING_IMAGES_WITH_TAG,
  START_SENDING_FILE_IMAGE,
  START_SENDING_URL_IMAGE,
} from '../store/image.actions';
import { HeaderService } from './header.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent implements OnInit {
  urlFormGroup!: FormGroup;
  fileFormGroup!: FormGroup;
  searchFormGroup!: FormGroup;
  selectedFile!: File;
  urlRegex = new RegExp(
    '[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&//=]*)'
  );
  fileError: boolean = false;

  constructor(private headerService : HeaderService){}

  ngOnInit(): void {
    this.urlFormGroup = new FormGroup({
      url: new FormControl(null, [
        Validators.pattern(this.urlRegex),
        Validators.required,
      ]),
    });
    this.fileFormGroup = new FormGroup({ file: new FormControl() });
    this.searchFormGroup = new FormGroup({"searchField" : new FormControl()})
  }

  onFileChanged(event: Event) {
    const file = this.headerService.validateFile(event.target as HTMLInputElement);
    if(!file){
      this.fileError = true;
      this.selectedFile = null!;
      return;
    }
    this.fileError = false;
    this.selectedFile = file;
  }
  
  onSubmitUrl() {
    const url = this.urlFormGroup.get('url')?.value;
    this.headerService.submitUrl(url);
    this.urlFormGroup.reset();
  }


  onSubmitFile() {
    this.headerService.submitFile(this.selectedFile);
  }

  onSearchSubmit(){
    const tag = this.searchFormGroup.get('searchField')?.value;
    this.headerService.submitSearch(tag);

  }
}
