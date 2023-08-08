package mwaris.dev.githubtrends.ui.trending_repositories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository

class TrendingRepositoriesListingViewModel(
    private val trendingListRepository: TrendingListRepository
) {

    private val mutableTrendingReposState = MutableLiveData<TrendingRepositoriesListingState>()
    val trendingReposState: LiveData<TrendingRepositoriesListingState> = mutableTrendingReposState

    fun getTrendingGithubRepositoriesList() {
        val repositoryState = trendingListRepository.getTrendingGithubRepositoriesList()
        mutableTrendingReposState.value = repositoryState
    }
}