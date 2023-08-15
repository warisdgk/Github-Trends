package mwaris.dev.githubtrends.data.remote

import mwaris.dev.githubtrends.ui.trendingrepositories.TrendingRepositoriesState

interface ITrendingListDataSource {
    suspend fun getTrendingGithubRepositoriesList(): TrendingRepositoriesState
}