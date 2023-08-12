package mwaris.dev.githubtrends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesITrendingListRepository(): ITrendingListRepository {
        return TrendingListRepository()
    }
}