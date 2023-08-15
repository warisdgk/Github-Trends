package mwaris.dev.githubtrends.data.remote.responses

import com.google.gson.annotations.SerializedName
import mwaris.dev.githubtrends.data.entities.Owner
import mwaris.dev.githubtrends.data.entities.Repository
import mwaris.dev.githubtrends.utils.Parselable


data class SearchTrendingResponse(
    @SerializedName("items")
    private val items: List<RepositoryItemsResponse>,

    ) : Parselable<List<Repository>> {

    override fun parse(): List<Repository> =
        items.map {
            it.parse()
        }.toList()

}

data class RepositoryItemsResponse(
    @SerializedName("id")
    private val id: String,

    @SerializedName("owner")
    private val owner: OwnerResponse,

    @SerializedName("name")
    private val name: String,

    @SerializedName("full_name")
    private val fullName: String,

    @SerializedName("description")
    private val description: String,

    @SerializedName("stargazers_count")
    private val stargazers_count: String?,

    @SerializedName("language")
    private val language: String?,

    ) : Parselable<Repository> {

    override fun parse(): Repository =
        Repository(
            id = id,
            name = name,
            fullName = fullName,
            description = description,
            avatarUrl = owner.parse().avatarUrl,
            stargazersCount = stargazers_count ?: "",
            language = language ?: "",
        )
}

data class OwnerResponse(
    @SerializedName("avatar_url")
    private val avatar_url: String,

    ) : Parselable<Owner> {

    override fun parse(): Owner =
        Owner(avatar_url)
}