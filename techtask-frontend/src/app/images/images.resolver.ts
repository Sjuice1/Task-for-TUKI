import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { Actions, ofType } from "@ngrx/effects";
import { Store } from "@ngrx/store";
import { ImageState } from "../store/image.reducer";
import { FETCH_IMAGES, START_FETCHING_IMAGES } from "../store/image.actions";
import { map, take } from "rxjs";

export const imagesResolver : ResolveFn<String[]> = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ) => {
    const store = inject(Store<{recipes : ImageState}>);
    const actions$ = inject(Actions);

    store.dispatch(START_FETCHING_IMAGES());
    return actions$.pipe(
        ofType(FETCH_IMAGES),
        take(1),
        map((answ) => answ.images)
    )
  }