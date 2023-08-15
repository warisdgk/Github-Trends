package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.base.BaseStateViewModel
import mwaris.dev.githubtrends.base.NetworkMonitor
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class TrendingRepositoriesListingViewModel @Inject constructor(
    private val trendingListRepository: ITrendingListRepository,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    networkMonitor: NetworkMonitor
) : BaseStateViewModel<TrendingRepositoriesScreenState>(
    savedStateHandle,
    TrendingRepositoriesScreenState()
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch {
            updateStateFor(TrendingRepositoriesState.Loading)
            val result = async(dispatcher) {
                trendingListRepository.getTrendingGithubRepositoriesList()
            }
            updateStateFor(result.await())
        }
    }

    private fun updateStateFor(trendingRepositoriesState: TrendingRepositoriesState) {
        when (trendingRepositoriesState) {
            TrendingRepositoriesState.Loading -> {
                mutableDataState.value = currentState().copy(isLoading = true)
            }

            is TrendingRepositoriesState.TrendingRepositories -> {
                mutableDataState.value = currentState().copy(
                    isLoading = false,
                    listOfRepositories = trendingRepositoriesState.repositories
                )
            }

            TrendingRepositoriesState.BackendError -> {
                mutableDataState.value = currentState().copy(
                    isLoading = false,
                    error = R.string.error_trending_repos_fetching
                )
            }
        }
    }
}