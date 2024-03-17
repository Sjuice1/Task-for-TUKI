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
  fileSizeError: boolean = false;

  constructor(private store: Store<{ images: ImageState }>) {}

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
    const maxFileSize = 2 * 1024 * 1024;
    const inputElement = event.target as HTMLInputElement;
    if (inputElement.files !== null) {
      this.selectedFile = inputElement.files[0];
    }
    if (this.selectedFile.size > maxFileSize) {
      this.selectedFile = null!;
      this.fileSizeError = true;
    } else {
      this.fileSizeError = false;
    }
  }
  
  onSubmitUrl() {
    this.store.dispatch(
      START_SENDING_URL_IMAGE({ url: this.urlFormGroup.get('url')?.value })
    );
    this.urlFormGroup.reset();
  }


  onSubmitFile() {
    const formData = new FormData();
    formData.append('file', this.selectedFile);
    this.store.dispatch(START_SENDING_FILE_IMAGE({ data: formData }));
  }

  onSearchSubmit(){
    if(this.searchFormGroup.get('searchField')?.value === null || this.searchFormGroup.get('searchField')?.value === "" ){
      
      this.store.dispatch(
        START_FETCHING_IMAGES()
      )
      return;
    }
    this.store.dispatch(
      START_FETCHING_IMAGES_WITH_TAG({tag: this.searchFormGroup.get('searchField')?.value})
    )
  }
}
