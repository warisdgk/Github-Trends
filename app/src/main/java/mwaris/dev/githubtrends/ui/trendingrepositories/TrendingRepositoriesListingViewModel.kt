package mwaris.dev.githubtrends.ui.trendingrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository

class TrendingRepositoriesListingViewModel(
    private val trendingListRepository: ITrendingListRepository
) {

    private val mutableTrendingReposListingState =
        MutableLiveData<TrendingRepositoriesListingState>()
    val trendingReposListingState: LiveData<TrendingRepositoriesListingState> =
        mutableTrendingReposListingState

    fun getTrendingGithubRepositoriesList() {
        mutableTrendingReposListingState.value = TrendingRepositoriesListingState.Loading

        val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()
        mutableTrendingReposListingState.value = repositoryState
    }
}