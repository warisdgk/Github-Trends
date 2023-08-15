package mwaris.dev.githubtrends.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mwaris.dev.githubtrends.base.ConnectivityManagerNetworkMonitor
import mwaris.dev.githubtrends.base.NetworkMonitor
import mwaris.dev.githubtrends.data.remote.ITrendingListDataSource
import mwaris.dev.githubtrends.data.remote.RemoteTrendingListDataSource
import mwaris.dev.githubtrends.data.repositories.ITrendingListRepository
import mwaris.dev.githubtrends.data.repositories.TrendingListRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesITrendingListRepository(
        remoteTrendingRepositoriesDataSource: ITrendingListDataSource
    ): ITrendingListRepository {
        return TrendingListRepository(remoteTrendingRepositoriesDataSource)
    }

    @Provides
    @Singleton
    fun providesIRemoteDataSource(): ITrendingListDataSource {
        return RemoteTrendingListDataSource()
    }

    @Provides
    @Singleton
    fun providesNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return ConnectivityManagerNetworkMonitor(context)
    }
}