package domain.product.repository

import domain.product.aggregate.Product
import java.util.*

interface ProductRepository {
    @Throws
    fun getAll(): List<Product>

    @Throws(NoSuchElementException::class)
    fun getById(id: UUID): Product

    @Throws
    fun update(product: Product): Product

    @Throws
    fun create(product: Product): Product

    @Throws
    fun delete(id: UUID)
}