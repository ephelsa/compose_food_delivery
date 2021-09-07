package com.github.ephelsa.framework.di

import com.github.ephelsa.data.CategoryRepository
import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.data.ShoppingCartRepository
import com.github.ephelsa.data.UserProfileRepository
import com.github.ephelsa.framework.CategoryRepositoryMock
import com.github.ephelsa.framework.ProductRepositoryMock
import com.github.ephelsa.framework.ShoppingCartRepositoryImpl
import com.github.ephelsa.framework.UserProfileRepositoryMock
import com.github.ephelsa.framework.local.dao.ShoppingCartDao
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

    @ViewModelScoped
    @Provides
    internal fun providesProductRepository(
        @IODispatcher dispatcher: CoroutineDispatcher
    ): ProductRepository {
        return ProductRepositoryMock(dispatcher)
    }

    @ViewModelScoped
    @Provides
    internal fun providesShoppingCartRepository(
        @IODispatcher dispatcher: CoroutineDispatcher,
        @ComposeFoodDeliveryDatabase shoppingCartDao: ShoppingCartDao,
        productRepository: ProductRepository
    ): ShoppingCartRepository {
        return ShoppingCartRepositoryImpl(dispatcher, shoppingCartDao, productRepository)
    }
}