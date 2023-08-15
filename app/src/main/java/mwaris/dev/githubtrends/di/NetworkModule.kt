package mwaris.dev.githubtrends.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mwaris.dev.githubtrends.BuildConfig
import mwaris.dev.githubtrends.utils.ConnectivityManagerNetworkMonitor
import mwaris.dev.githubtrends.utils.NetworkMonitor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_DEFAULT = 30L

    @Provides
    @Singleton
    fun providesNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return ConnectivityManagerNetworkMonitor(context)
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesOkhttpClient(): OkHttpClient.Builder {
        val connectionPool = ConnectionPool()
        return OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .connectTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DEFAULT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
    }

    @Provides
    @Named("BASE_URL")
    fun provideAppBaseUrl(): String = BuildConfig.BASE_API_URL

    @TrendingRepositoriesAPIClient
    @Provides
    @Singleton
    fun providesReposRetrofitInstance(
        @ApplicationContext context: Context,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(provideAppBaseUrl())
            .addConverterFactory(providesConverterFactory())
            .client(
                providesOkhttpClient()
                    .build()
            )
            .build()
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TrendingRepositoriesAPIClient