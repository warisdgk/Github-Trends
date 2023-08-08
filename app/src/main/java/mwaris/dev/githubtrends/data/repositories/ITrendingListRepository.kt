package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.ui.trending_repositories.list.TrendingRepositoriesListingState

interface ITrendingListRepository {
    fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState
}
