package mwaris.dev.githubtrends.data.repositories

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState

interface ITrendingListRepository {
    suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesScreenState
}
