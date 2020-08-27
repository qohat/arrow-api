package com.arrow.products.application

import com.arrow.products.domain.Product
import com.arrow.products.domain.ProductCategory
import com.arrow.products.domain.ProductId
import com.arrow.products.domain.ProductName

typealias ProductCore = com.arrow.core.domain.Product
typealias ProductCoreId = com.arrow.core.domain.ProductId
typealias ProductCoreName = com.arrow.core.domain.ProductName
typealias ProductCoreCategory = com.arrow.core.domain.ProductCategory

fun Product.toCore(): ProductCore =
    ProductCore(
        id.toCore(),
        name.toCore(),
        category.toCore()
    )

fun ProductId.toCore(): ProductCoreId = ProductCoreId(value)

fun ProductName.toCore(): ProductCoreName = ProductCoreName(value)

fun ProductCategory.toCore(): ProductCoreCategory = ProductCoreCategory(value)

fun ProductCore.toDomain(): Product =
    Product(
        id.toDomain(),
        name.toDomain(),
        category.toDomain()
    )

fun ProductCoreId.toDomain(): ProductId = ProductId(value)

fun ProductCoreName.toDomain(): ProductName = ProductName(value)

fun ProductCoreCategory.toDomain(): ProductCategory = ProductCategory(value)
