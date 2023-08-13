package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mwaris.dev.githubtrends.base.BaseStateViewModel
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class TrendingRepositoriesListingViewModel @Inject constructor(
    private val trendingListRepository: ITrendingListRepository,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseStateViewModel<TrendingRepositoriesScreenState>(
    savedStateHandle,
    TrendingRepositoriesScreenState()
) {

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch {

            mutableDataState.value = currentState().copy(isLoading = true)

            val result = async(dispatcher) {
                trendingListRepository.getTrendingGithubRepositoriesList()
            }

            mutableDataState.value = currentState().copy(
                isLoading = result.await().isLoading,
                listOfRepositories = result.await().listOfRepositories,
                error = result.await().error
            )

        }
    }
}