package com.github.ephelsa.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.ephelsa.framework.local.dao.ShoppingCartDao
import com.github.ephelsa.framework.local.entity.ShoppingCartEntity

@Database(
    entities = [
        ShoppingCartEntity::class
    ],
    version = 1
)
abstract class ComposeFoodDeliveryDB : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "compose-food-delivery-database"

        @Volatile
        private var instance: ComposeFoodDeliveryDB? = null

        fun getInstance(context: Context): ComposeFoodDeliveryDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }

        private fun buildDatabase(context: Context): ComposeFoodDeliveryDB {
            return Room.databaseBuilder(context, ComposeFoodDeliveryDB::class.java, DATABASE_NAME)
                .build()
        }
    }

    abstract fun shoppingCartDao(): ShoppingCartDao
}