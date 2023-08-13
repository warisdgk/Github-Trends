package mwaris.dev.githubtrends.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    val id: String,
    val name: String,
    val fullName: String,
    val avatarUrl: String,
    val description: String
) : Parcelable