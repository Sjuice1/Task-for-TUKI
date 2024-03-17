import { createReducer, on } from "@ngrx/store"
import { FETCH_IMAGES, SEND_IMAGE, START_FETCHING_IMAGES, START_SENDING_FILE_IMAGE, START_SENDING_URL_IMAGE } from "./image.actions"

export interface ImageState {
    images : string[],
    loading : boolean
}

const initState: ImageState = {
    images: null!,
    loading : false
}

export const imageReducer = createReducer(
    initState,
    on(START_FETCHING_IMAGES,(initState,action) => {
        return {
            images : initState.images,
            loading: true
        }
    }),
    on(FETCH_IMAGES,(initState,action) => {
        return {
            images : action.images,
            loading: false
        }
    }),
    on(START_SENDING_FILE_IMAGE,(initState,action) => {
        return {
            images : initState.images,
            loading: true
        }
    }),
    on(START_SENDING_URL_IMAGE,(initState,action) => {
        return {
            images : initState.images,
            loading: true
        }
    }),
    on(SEND_IMAGE,(initState,action) => {
        return {
            images : [...initState.images,action.imageUrl],
            loading:false
        }
    })
)