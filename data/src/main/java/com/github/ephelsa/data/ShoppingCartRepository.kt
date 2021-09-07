package com.github.ephelsa.data

import com.github.ephelsa.domain.ProductQuantity
import com.github.ephelsa.domain.ProductWithID
import kotlinx.coroutines.flow.Flow

interface ShoppingCartRepository {

    suspend fun shoppingCartProducts(): Flow<List<ProductQuantity>>

    suspend fun addProducts(vararg productWithID: ProductWithID): Result<Unit>

    suspend fun removeProducts(vararg productWithID: ProductWithID): Result<Unit>
}