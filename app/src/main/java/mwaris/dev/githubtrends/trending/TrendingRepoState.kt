package mwaris.dev.githubtrends.trending

sealed class TrendingRepoState {
    data class GithubRepositories(val repos: List<Repository>) : TrendingRepoState()
}