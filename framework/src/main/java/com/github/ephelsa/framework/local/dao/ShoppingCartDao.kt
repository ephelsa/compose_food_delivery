package com.github.ephelsa.framework.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.ephelsa.domain.ProductWithID
import com.github.ephelsa.framework.local.entity.ShoppingCartEntity
import com.github.ephelsa.framework.local.join.ShoppingCartItemQuantity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {

    @Query("SELECT real_id, COUNT(real_id) as quantity FROM SHOPPING_CART GROUP BY real_id")
    fun fetchProductsGroupedByQuantity(): Flow<List<ShoppingCartItemQuantity>>

    @Insert
    fun insertAll(vararg cart: ShoppingCartEntity)

    @Query("DELETE FROM SHOPPING_CART WHERE real_id IN (:ids)")
    fun deleteAll(ids: List<String>)
}