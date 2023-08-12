package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesListingState

interface ITrendingListRepository {
    fun getTrendingGithubRepositoriesList(): TrendingRepositoriesListingState
}
