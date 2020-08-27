package com.arrow.products.domain.persistence

import arrow.Kind
import arrow.core.Option
import com.arrow.products.domain.Product
import com.arrow.products.domain.ProductId

interface ProductRepository<F> {

    fun findBy(id: ProductId): Kind<F, Option<Product>>

    fun findAll(): Kind<F, List<Product>>

    fun save(product: Product): Kind<F, Unit>

    fun deleteBy(id: ProductId): Kind<F, Boolean>
}
