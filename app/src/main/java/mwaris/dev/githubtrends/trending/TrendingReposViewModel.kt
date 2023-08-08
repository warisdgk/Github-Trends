package mwaris.dev.githubtrends.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TrendingReposViewModel {

    private val mutableTrendingReposState =  MutableLiveData<TrendingRepoState>()
    val trendingReposState : LiveData<TrendingRepoState> =  mutableTrendingReposState


    fun getTrendingRepos() {
        mutableTrendingReposState.value = TrendingRepoState.GithubRepositories(emptyList())
    }
}