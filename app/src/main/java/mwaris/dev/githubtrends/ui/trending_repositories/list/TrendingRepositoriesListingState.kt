package mwaris.dev.githubtrends.ui.trending_repositories.list

import mwaris.dev.githubtrends.data.entities.Repository

sealed class TrendingRepositoriesListingState {
    object BackendError : TrendingRepositoriesListingState()

    data class GithubRepositories(val repos: List<Repository>) : TrendingRepositoriesListingState()
}