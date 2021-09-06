package com.github.ephelsa.framework

import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.Recommended
import com.github.ephelsa.framework.BurgersMock.FatBurgerOriginal
import com.github.ephelsa.framework.BurgersMock.RedRobinsAvocado
import com.github.ephelsa.framework.BurgersMock.TheOriginalGrassFedBurger
import com.github.ephelsa.framework.PizzasMock.Napolitana
import com.github.ephelsa.framework.SushiesMock.Octopussy
import com.github.ephelsa.framework.SushiesMock.Oma
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ProductRepositoryMock(
    override val coroutineContext: CoroutineContext
) : ProductRepository, CoroutineScope {

    private val products = listOf(
        RedRobinsAvocado,
        TheOriginalGrassFedBurger,
        FatBurgerOriginal,
        Napolitana,
        Octopussy,
        Oma
    )

    override suspend fun getRecommended(categoryType: Category.CategoryType?): List<Recommended> = withContext(coroutineContext) {
        if (categoryType == null)
            withoutCategoryType()
        else
            withCategoryType(categoryType)
    }

    private suspend fun withCategoryType(categoryType: Category.CategoryType): List<Recommended> {
        delay(2_000)

        return products.filter {
            it.categoryType == categoryType
        }.map(Product::toRecommended)
    }

    private suspend fun withoutCategoryType(): List<Recommended> {
        delay(3_000)

        return listOf(
            RedRobinsAvocado,
            Napolitana,
            Octopussy
        ).map(Product::toRecommended)
    }

    override suspend fun getProductDetails(productId: String): Product? {
        return products.find {
            productId == it.getRealID()
        }
    }
}