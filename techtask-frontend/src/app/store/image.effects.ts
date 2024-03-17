import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {
  FETCH_ERROR,
  FETCH_IMAGES,
  SEND_IMAGE,
  START_FETCHING_IMAGES,
  START_FETCHING_IMAGES_WITH_TAG,
  START_SENDING_FILE_IMAGE,
  START_SENDING_URL_IMAGE,
} from './image.actions';
import { catchError, map, of, switchMap } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable()
export class ImageEffects {
  apiLink = environment.apiLink;

  constructor(private actions$: Actions, private httpClient: HttpClient) {}

  startFetching = createEffect(() =>
  this.actions$.pipe(
    ofType(START_FETCHING_IMAGES),
    switchMap(() =>
      this.httpClient.get<string[]>(`${this.apiLink}api/image`).pipe(
        map((answData) => {
          console.log(answData);
          return FETCH_IMAGES({ images: answData });
        }),
        catchError(err => {
          console.error('An error occurred:', err);
          return of(FETCH_ERROR());
        })
      )
    )
  )
);

  startFetchingWithTags = createEffect(() =>
    this.actions$.pipe(
      ofType(START_FETCHING_IMAGES_WITH_TAG),
      switchMap((action) => {
        const params = new HttpParams().set('tag', action.tag);

        return this.httpClient
          .get<string[]>(`${this.apiLink}api/image`, { params })
          .pipe(
            map((answData) => {
              return FETCH_IMAGES({ images: answData });
            }),
            catchError(err => {
              console.error('An error occurred:', err);
              return of(FETCH_ERROR());
            })
          );
      })
    )
  );

  sendFileImage = createEffect(() =>
    this.actions$.pipe(
      ofType(START_SENDING_FILE_IMAGE),
      switchMap((action) => {
        return this.httpClient
          .post(`${this.apiLink}api/image/file`, action.data, {
            responseType: 'text',
          })
          .pipe(
            map((answData) => {
              return SEND_IMAGE({ imageUrl: answData });
            }),
            catchError(err => {
              console.error('An error occurred:', err);
              return of(FETCH_ERROR());
            })
          );
      })
    )
  );

  sendUrlImage = createEffect(() =>
    this.actions$.pipe(
      ofType(START_SENDING_URL_IMAGE),
      switchMap((action) => {
        const params = new HttpParams().set('image', action.url);
        console.log(action.url);

        return this.httpClient
          .post(`${this.apiLink}api/image/url`, null, {
            params,
            responseType: 'text',
          })
          .pipe(
            map((answData) => {
              return SEND_IMAGE({ imageUrl: answData });
            }),
            catchError(err => {
              console.error('An error occurred:', err);
              return of(FETCH_ERROR());
            })
          );
      })
    )
  );
}
