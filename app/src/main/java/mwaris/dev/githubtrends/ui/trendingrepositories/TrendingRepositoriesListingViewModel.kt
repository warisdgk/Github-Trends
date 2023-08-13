package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    TrendingRepositoriesScreenState(
        false,
        emptyList()
    )
) {

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch {
            withContext(dispatcher) {
                mutableDataState.value = currentState().copy(isLoading = true)

                val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()

                mutableDataState.value = currentState().copy(
                    isLoading = repositoryState.isLoading,
                    listOfRepositories = repositoryState.listOfRepositories,
                    error = repositoryState.error
                )
            }
        }
    }
}