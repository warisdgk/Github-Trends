package mwaris.dev.githubtrends.ui.trendingrepositories

import mwaris.dev.githubtrends.data.entities.Repository

sealed class TrendingRepositoriesListingState {
    object Loading : TrendingRepositoriesListingState()

    object BackendError : TrendingRepositoriesListingState()

    data class GithubRepositories(val repos: List<Repository>) : TrendingRepositoriesListingState()
}