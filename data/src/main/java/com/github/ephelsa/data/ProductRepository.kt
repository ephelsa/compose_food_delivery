package com.github.ephelsa.data

import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.Recommended

interface ProductRepository {

    suspend fun getRecommended(categoryType: Category.CategoryType?): List<Recommended>

    suspend fun getProductDetails(productId: String): Product?
}