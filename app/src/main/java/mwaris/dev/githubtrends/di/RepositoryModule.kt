package mwaris.dev.githubtrends.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mwaris.dev.githubtrends.data.remote.ITrendingListDataSource
import mwaris.dev.githubtrends.data.remote.ITrendingRepositoriesApi
import mwaris.dev.githubtrends.data.remote.TrendingRepositoriesRemoteDataSource
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        trendingRepositoriesApi: ITrendingRepositoriesApi
    ): ITrendingListDataSource {
        return TrendingRepositoriesRemoteDataSource(trendingRepositoriesApi)
    }

    @Provides
    @Singleton
    fun providesGithubAPI(
        @TrendingRepositoriesAPIClient retrofit: Retrofit
    ): ITrendingRepositoriesApi {
        return retrofit.create(ITrendingRepositoriesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesTrendingListRepository(
        remoteTrendingRepositoriesDataSource: ITrendingListDataSource
    ): ITrendingListRepository {
        return TrendingListRepository(remoteTrendingRepositoriesDataSource)
    }
}