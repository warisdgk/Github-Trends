package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class TrendingRepositoriesListingViewModel @Inject constructor(
    private val trendingListRepository: ITrendingListRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val mutableTrendingReposListingState =
        MutableLiveData<TrendingRepositoriesScreenState>()
    val trendingReposListingState: LiveData<TrendingRepositoriesScreenState> =
        mutableTrendingReposListingState

    fun getTrendingGithubRepositoriesList() {
        viewModelScope.launch {
            withContext(dispatcher) {
                mutableTrendingReposListingState.value =
                    TrendingRepositoriesScreenState(isLoading = true)

                val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()
                mutableTrendingReposListingState.value = repositoryState
            }
        }
    }
}