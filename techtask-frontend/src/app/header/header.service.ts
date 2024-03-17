import { Injectable } from "@angular/core";
import { Store } from "@ngrx/store";
import { ImageState } from "../store/image.reducer";
import { START_FETCHING_IMAGES, START_FETCHING_IMAGES_WITH_TAG, START_SENDING_FILE_IMAGE, START_SENDING_URL_IMAGE } from "../store/image.actions";

@Injectable({providedIn:'root'})
export class HeaderService{

    constructor(private store: Store<{ images: ImageState }>) {}
    
    submitUrl(url : string){
        this.store.dispatch(
            START_SENDING_URL_IMAGE({url : url})
          );
    }

    submitFile(file: File) {
        const formData = new FormData();
        formData.append('file', file);
        this.store.dispatch(START_SENDING_FILE_IMAGE({ data: formData }));
      }

      submitSearch(tag: any) {
        if(tag === null || tag === ""){
            this.store.dispatch(
              START_FETCHING_IMAGES()
            )
            return;
          }
          this.store.dispatch(
            START_FETCHING_IMAGES_WITH_TAG({tag: tag})
          )
        }

      validateFile(target: HTMLInputElement) : File | null {
        var file;
        const maxFileSize = 2 * 1024 * 1024;
        const inputElement = target;
    
        if (inputElement.files !== null) {
          file = inputElement.files[0];
        }
        else{
            return null;
        }
        
        if(!file.type.includes('image')){
          return null;
        }
        
        if (file.size > maxFileSize) {
          return null;
        }
        return file; 
      }
}