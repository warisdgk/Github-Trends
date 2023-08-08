package mwaris.dev.githubtrends.ui.trending_repositories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository

class TrendingRepositoriesListingViewModel(
    private val trendingListRepository: ITrendingListRepository
) {

    private val mutableTrendingReposListingState = MutableLiveData<TrendingRepositoriesListingState>()
    val trendingReposListingState: LiveData<TrendingRepositoriesListingState> = mutableTrendingReposListingState

    fun getTrendingGithubRepositoriesList() {
        val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()
        mutableTrendingReposListingState.value = repositoryState
    }
}