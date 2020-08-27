package com.arrow.products.adapter.out.persistence.memory

import arrow.Kind
import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.fx.Ref
import arrow.fx.typeclasses.Effect
import com.arrow.products.domain.Product
import com.arrow.products.domain.ProductId
import com.arrow.products.domain.persistence.ProductRepository

typealias Store<F> = Ref<F, Map<String, Product>>

class InMemoryProductRepository<F> private constructor(
    val Q: Effect<F>,
    val store: Store<F>
) : ProductRepository<F>, Effect<F> by Q {

    override fun findBy(id: ProductId): Kind<F, Option<Product>> =
        store.get().map { Option.fromNullable(it[id.value]) }

    override fun findAll(): Kind<F, List<Product>> =
        store.get().map { it.values.toList() }

    override fun save(product: Product): Kind<F, Unit> =
        findBy(product.id).flatMap {
            when (it) {
                is None -> store.update { it.plus(Pair(product.id.value, product)) }
                else -> raiseError(RuntimeException("Failed saving"))
            }
        }

    override fun deleteBy(id: ProductId): Kind<F, Boolean> =
        findBy(id).flatMap {
            when (it) {
                is Some -> store.update { it.minus(id.value) }.map { true }
                else -> just(false)
            }
        }

    companion object {
        fun <F> make(Q: Effect<F>, store: Store<F>): ProductRepository<F> =
            InMemoryProductRepository(Q, store)
    }
}
