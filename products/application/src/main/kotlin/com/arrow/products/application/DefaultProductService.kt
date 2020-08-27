package com.arrow.products.application

import arrow.Kind
import arrow.core.Some
import arrow.fx.typeclasses.Effect
import com.arrow.core.domain.Product
import com.arrow.core.domain.ProductId
import com.arrow.products.domain.persistence.ProductRepository
import java.lang.RuntimeException

class DefaultProductService<F> private constructor(
    val Q: Effect<F>,
    val productRepository: ProductRepository<F>
) : ProductService<F>, Effect<F> by Q {
    override fun findBy(id: ProductId): Kind<F, Product> =
        productRepository.findBy(id.toDomain()).map {
            when (it) {
                is Some -> it.t.toCore()
                else -> throw RuntimeException("Failed getting record")
            }
        }

    override fun findAll(): Kind<F, List<Product>> =
        productRepository.findAll().map {
            it.map {
                it.toCore()
            }
        }

    override fun save(product: Product): Kind<F, Unit> =
        productRepository.save(product.toDomain())

    override fun deleteBy(id: ProductId): Kind<F, Boolean> =
        productRepository.deleteBy(id.toDomain())
}
