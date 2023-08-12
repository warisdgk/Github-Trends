package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository

class TrendingRepositoriesListingViewModel(
    private val trendingListRepository: ITrendingListRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableTrendingReposListingState =
        MutableLiveData<TrendingRepositoriesListingState>()
    val trendingReposListingState: LiveData<TrendingRepositoriesListingState> =
        mutableTrendingReposListingState

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch {
            withContext(dispatcher) {
                mutableTrendingReposListingState.value = TrendingRepositoriesListingState.Loading

                val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()
                mutableTrendingReposListingState.value = repositoryState
            }
        }
    }
}