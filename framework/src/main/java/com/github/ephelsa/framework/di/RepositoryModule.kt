package com.github.ephelsa.framework.di

import com.github.ephelsa.data.CategoryRepository
import com.github.ephelsa.data.UserProfileRepository
import com.github.ephelsa.framework.CategoryRepositoryMock
import com.github.ephelsa.framework.UserProfileRepositoryMock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    internal fun providesUserProfileRepository(
        @IODispatcher dispatcher: CoroutineDispatcher
    ): UserProfileRepository {
        return UserProfileRepositoryMock(dispatcher)
    }

    @ViewModelScoped
    @Provides
    internal fun providesCategoryRepository(
        @IODispatcher dispatcher: CoroutineDispatcher
    ): CategoryRepository {
        return CategoryRepositoryMock(dispatcher)
    }
}