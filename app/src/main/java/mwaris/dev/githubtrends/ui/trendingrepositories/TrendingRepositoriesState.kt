package mwaris.dev.githubtrends.ui.trendingrepositories

import mwaris.dev.githubtrends.data.entities.Repository

sealed class TrendingRepositoriesState {

    object Loading : TrendingRepositoriesState()

    data class TrendingRepositories(val repositories: List<Repository>) :
        TrendingRepositoriesState()

    object BackendError : TrendingRepositoriesState()

}
