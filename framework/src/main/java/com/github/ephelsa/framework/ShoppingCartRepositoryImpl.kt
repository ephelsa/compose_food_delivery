package com.github.ephelsa.framework

import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.data.ShoppingCartRepository
import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.ProductQuantity
import com.github.ephelsa.domain.ProductWithID
import com.github.ephelsa.framework.local.dao.ShoppingCartDao
import com.github.ephelsa.framework.local.entity.ShoppingCartEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ShoppingCartRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val shoppingCartDao: ShoppingCartDao,
    private val productRepository: ProductRepository
) : ShoppingCartRepository {

    override suspend fun shoppingCartProducts(): Flow<List<ProductQuantity>> {
        return shoppingCartDao.fetchProductsGroupedByQuantity()
            .map { list ->
                list.mapNotNull {
                    val product = fetchProductRemotely(it)

                    if (product != null)
                        ProductQuantity(product, it.quantity)
                    else
                        null
                }
            }
    }

    private suspend fun fetchProductRemotely(productWithID: ProductWithID): Product? {
        return productRepository.getProductDetails(productWithID.realID)
    }

    override suspend fun addProducts(vararg productWithID: ProductWithID): Result<Unit> = withContext(dispatcher) {
        try {
            val result = shoppingCartDao.insertAll(*createShoppingCart(productWithID))
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun createShoppingCart(productWithID: Array<out ProductWithID>): Array<ShoppingCartEntity> {
        return productWithID.map { ShoppingCartEntity(it.realID) }.toTypedArray()
    }

    override suspend fun removeProducts(vararg productWithID: ProductWithID): Result<Unit> = withContext(dispatcher) {
        try {
            val result = shoppingCartDao.deleteAll(productWithID.map(ProductWithID::realID))
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}