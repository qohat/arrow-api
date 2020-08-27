package com.arrow.products.domain

import java.util.UUID

data class ProductId(val value: String = UUID.randomUUID().toString())

data class ProductName(val value: String)

data class ProductCategory(val value: String)

data class Product(
    val id: ProductId,
    val name: ProductName,
    val category: ProductCategory
)
