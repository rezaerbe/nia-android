package com.erbe.nowinandroid.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.erbe.nowinandroid.core.network.datasource.NiaRemoteDataSource
import com.erbe.nowinandroid.core.network.datasource.NiaRemoteDataSourceImpl
import com.erbe.nowinandroid.core.network.interceptor.ConnectionStatusInterceptor
import com.erbe.nowinandroid.core.network.service.NiaService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindNiaRemoteDataSource(
        niaRemoteDataSourceImpl: NiaRemoteDataSourceImpl
    ): NiaRemoteDataSource

    companion object {
        @Provides
        fun provideChuckerCollector(
            @ApplicationContext context: Context
        ): ChuckerCollector {
            return ChuckerCollector(
                context,
                true,
                RetentionManager.Period.ONE_HOUR
            )
        }

        @Provides
        fun provideChuckerInterceptor(
            @ApplicationContext context: Context,
            chuckerCollector: ChuckerCollector
        ): ChuckerInterceptor {
            return ChuckerInterceptor.Builder(context)
                .collector(chuckerCollector)
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        }

        @Provides
        fun provideOkHttpClient(
            connectionStatusInterceptor: ConnectionStatusInterceptor,
            chuckerInterceptor: ChuckerInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(connectionStatusInterceptor)
                .addInterceptor(chuckerInterceptor)
                .build()
        }

        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl("http://192.168.25.125:8080/")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
        }

        @Provides
        @Singleton
        fun provideNiaService(builder: Retrofit.Builder): NiaService {
            return builder
                .build()
                .create(NiaService::class.java)
        }
    }
}