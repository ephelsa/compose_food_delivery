package com.github.ephelsa.framework.di

import android.content.Context
import com.github.ephelsa.framework.local.ComposeFoodDeliveryDB
import com.github.ephelsa.framework.local.dao.ShoppingCartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    @ComposeFoodDeliveryDatabase
    internal fun providesComposeFoodDeliveryDatabase(
        @ApplicationContext context: Context
    ): ComposeFoodDeliveryDB {
        return ComposeFoodDeliveryDB.getInstance(context)
    }

    @Provides
    @ComposeFoodDeliveryDatabase
    internal fun providesShoppingCartDao(
        @ComposeFoodDeliveryDatabase composeFoodDeliveryDB: ComposeFoodDeliveryDB
    ): ShoppingCartDao {
        return composeFoodDeliveryDB.shoppingCartDao()
    }
}