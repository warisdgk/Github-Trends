package mwaris.dev.githubtrends.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatarUrl: String,
) : Parcelable