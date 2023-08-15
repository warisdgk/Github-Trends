package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mwaris.dev.githubtrends.R
import mwaris.dev.githubtrends.base.viewmodel.BaseStateViewModel
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.di.IoDispatcher
import mwaris.dev.githubtrends.utils.NetworkMonitor
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

    private val handler = CoroutineExceptionHandler { _, _ ->
        updateStateFor(TrendingRepositoriesState.BackendError)
    }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch(handler) {
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
                mutableDataState.postValue(currentState().copy(isLoading = true))
            }

            is TrendingRepositoriesState.TrendingRepositories -> {
                mutableDataState.postValue(
                    currentState().copy(
                        isLoading = false,
                        listOfRepositories = trendingRepositoriesState.repositories
                    )
                )
            }

            TrendingRepositoriesState.BackendError -> {
                mutableDataState.postValue(
                    currentState().copy(
                        isLoading = false,
                        error = R.string.error_trending_repos_fetching
                    )
                )
            }
        }
    }
}