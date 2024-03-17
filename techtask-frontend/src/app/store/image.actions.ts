import { createAction, props } from "@ngrx/store";

export const START_FETCHING_IMAGES = createAction(
    '[Images] Start Fetching'
)

export const START_FETCHING_IMAGES_WITH_TAG = createAction(
    '[Images] Start fetching images with tag',
    props<{tag : string}>()
)

export const FETCH_IMAGES = createAction(
    '[Images] Fetch images',
    props<{images : string[]}>()
)

export const START_SENDING_FILE_IMAGE = createAction(
    '[Images] Start sending file image',
    props<{data : FormData}>()
)

export const START_SENDING_URL_IMAGE = createAction(
    '[Images] Start sending url image',
    props<{url : string}>()
)

export const SEND_IMAGE = createAction(
    '[Images] Send image',
    props<{imageUrl : string}>()
)
