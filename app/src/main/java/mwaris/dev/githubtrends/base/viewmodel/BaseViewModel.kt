package mwaris.dev.githubtrends.base.viewmodel

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<DataState : Parcelable>(
) : ViewModel() {

    protected abstract val mutableDataState: MutableLiveData<DataState>
    open val dataState: LiveData<DataState> by lazy { mutableDataState }

    protected fun currentState(): DataState = dataState.value!!
}