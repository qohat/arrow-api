package com.arrow.core.domain

data class ProductId(val value: String)

data class ProductName(val value: String)

data class ProductCategory(val value: String)

data class Product(
    val id: ProductId,
    val name: ProductName,
    val category: ProductCategory
)
