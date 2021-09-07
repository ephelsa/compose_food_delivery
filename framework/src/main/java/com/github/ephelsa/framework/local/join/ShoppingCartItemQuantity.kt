package com.github.ephelsa.framework.local.join

import androidx.room.ColumnInfo
import com.github.ephelsa.domain.ProductWithID

data class ShoppingCartItemQuantity(
    @ColumnInfo(name = "real_id") override val realID: String,
    @ColumnInfo(name = "quantity") val quantity: Int
) : ProductWithID
