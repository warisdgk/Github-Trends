package mwaris.dev.githubtrends.ui.trendingrepositories

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize
import mwaris.dev.githubtrends.data.entities.Repository
@Parcelize
data class TrendingRepositoriesScreenState(
    val isLoading: Boolean = false,
    val listOfRepositories: List<Repository> = emptyList(),
    @StringRes val error: Int = 0
) : Parcelable
