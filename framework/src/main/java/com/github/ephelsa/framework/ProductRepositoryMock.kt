package com.github.ephelsa.framework

import com.github.ephelsa.data.ProductRepository
import com.github.ephelsa.domain.Category
import com.github.ephelsa.domain.Product
import com.github.ephelsa.domain.Recommended
import com.github.ephelsa.framework.BurgersMock.FatBurgerOriginal
import com.github.ephelsa.framework.BurgersMock.RedRobinsAvocado
import com.github.ephelsa.framework.BurgersMock.TheOriginalGrassFedBurger
import com.github.ephelsa.framework.PizzasMock.Napolitana
import com.github.ephelsa.framework.di.IODispatcher
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@ViewModelScoped
class ProductRepositoryMock @Inject constructor(
    @IODispatcher override val coroutineContext: CoroutineContext
) : ProductRepository, CoroutineScope {

    private val products = listOf(RedRobinsAvocado, TheOriginalGrassFedBurger, FatBurgerOriginal, Napolitana)

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

        return listOf(RedRobinsAvocado, Napolitana).map(Product::toRecommended)
    }

    override suspend fun getProductDetails(productId: String): Product? {
        return products.find {
            productId == it.getRealID()
        }
    }
}