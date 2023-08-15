package mwaris.dev.githubtrends.data.remote

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesScreenState

interface ITrendingListDataSource {
    suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesScreenState
}