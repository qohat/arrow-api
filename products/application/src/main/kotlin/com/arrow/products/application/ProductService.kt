package com.arrow.products.application

import arrow.Kind
import com.arrow.core.domain.Product
import com.arrow.core.domain.ProductId

interface ProductService<F> {

    fun findBy(id: ProductId): Kind<F, Product>

    fun findAll(): Kind<F, List<Product>>

    fun save(product: Product): Kind<F, Unit>

    fun deleteBy(id: ProductId): Kind<F, Boolean>
}
