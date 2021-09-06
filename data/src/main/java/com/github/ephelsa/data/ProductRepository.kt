package com.github.ephelsa.data

import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.Recommended

interface ProductRepository {

    suspend fun getRecommended(categoryId: String?): List<Recommended>

    suspend fun getProductDetails(productId: String): Product
}