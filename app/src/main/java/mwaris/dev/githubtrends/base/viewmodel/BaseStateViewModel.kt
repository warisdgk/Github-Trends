package mwaris.dev.githubtrends.base.viewmodel

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle

abstract class BaseStateViewModel<DataState : Parcelable>(
    savedStateHandle: SavedStateHandle,
    initDataState: DataState
) : BaseViewModel<DataState>() {

    override val mutableDataState by lazy {
        savedStateHandle.getLiveData("mainMutableDataState", initDataState)
    }
}