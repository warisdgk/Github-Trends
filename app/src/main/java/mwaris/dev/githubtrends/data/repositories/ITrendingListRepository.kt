package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState

interface ITrendingListRepository {
    suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesState
}
