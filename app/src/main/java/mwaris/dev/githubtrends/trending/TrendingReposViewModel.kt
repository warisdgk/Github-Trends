package mwaris.dev.githubtrends.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TrendingReposViewModel {

    private val mutableTrendingReposState = MutableLiveData<TrendingRepoState>()
    val trendingReposState: LiveData<TrendingRepoState> = mutableTrendingReposState


    fun getTrendingRepos(signalEmptyData: Boolean) {
        if (signalEmptyData) {
            mutableTrendingReposState.value = TrendingRepoState.GithubRepositories(emptyList())
        } else {
            mutableTrendingReposState.value = TrendingRepoState.GithubRepositories(
                listOf(
                    Repositories(
                        "23096959",
                        "go",
                        "golang/go",
                        "https://avatars.githubusercontent.com/u/4314092?v=4",
                        "The Go programming language"
                    )
                )
            )
        }
    }
}