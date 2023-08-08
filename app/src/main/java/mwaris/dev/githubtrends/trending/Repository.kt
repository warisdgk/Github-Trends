package mwaris.dev.githubtrends.trending

data class Repository(
    val id: String,
    val name: String,
    val fullName: String,
    val avatarUrl: String,
    val description: String
)