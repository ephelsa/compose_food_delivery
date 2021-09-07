package com.github.ephelsa.framework.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.ephelsa.domain.ProductWithID

@Entity(
    tableName = "SHOPPING_CART"
)
data class ShoppingCartEntity(
    @ColumnInfo(name = "real_id")
    override val realID: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
) : ProductWithID
