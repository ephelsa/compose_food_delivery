package com.github.ephelsa.framework

import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.Recommended
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

class ProductRepositoryMock(
    override val coroutineContext: CoroutineContext
) : ProductRepository, CoroutineScope {

    override suspend fun getRecommended(categoryId: String?): List<Recommended> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetails(productId: String): Product {
        TODO("Not yet implemented")
    }
}