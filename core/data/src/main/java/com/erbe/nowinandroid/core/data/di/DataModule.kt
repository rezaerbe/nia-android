package com.erbe.nowinandroid.core.data.di

import com.erbe.nowinandroid.core.data.repository.NiaRemoteRepository
import com.erbe.nowinandroid.core.data.repository.NiaRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindNiaRemoteRepository(
        niaRemoteRepositoryImpl: NiaRemoteRepositoryImpl
    ): NiaRemoteRepository
}