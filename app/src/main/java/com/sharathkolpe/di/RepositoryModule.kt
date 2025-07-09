package com.sharathkolpe.di

import com.sharathkolpe.firebaseAuth.repository.AuthRepository
import com.sharathkolpe.firebaseAuth.repository.AuthRepositoryImpl
import com.sharathkolpe.firebaseRealTimeDB.repository.RealTimeDbRepository
import com.sharathkolpe.firebaseRealTimeDB.repository.RealTimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRealTimeRepository(
        repo: RealTimeDbRepository
    ): RealTimeRepository

    @Binds
    abstract fun providesFirebaseAuthRepository(
        repo: AuthRepositoryImpl
    ): AuthRepository
}