package domain.product.repository.memory

import domain.product.aggregate.Product
import domain.product.repository.ProductRepository
import java.util.*

class MemoryRepository(
    private val productsStorage: MutableMap<UUID, Product> = mutableMapOf()
) : ProductRepository {
    override fun create(product: Product): Product {
        synchronized(productsStorage) {
            productsStorage[product.getId()] = product
        }
        return product
    }

    override fun delete(id: UUID) {
        synchronized(productsStorage) {
            productsStorage.remove(id)
        }
    }

    override fun getAll(): List<Product> {
        val products = mutableListOf<Product>()

        synchronized(productsStorage) {
            for (product in productsStorage.values) {
                products.add(product)
            }
        }

        return products
    }

    @Throws(NoSuchElementException::class)
    override fun getById(id: UUID): Product =
        productsStorage[id] ?: throw NoSuchElementException("Product with id $id not found")

    override fun update(product: Product): Product = create(product)

    companion object {
        fun create(): MemoryRepository {
            return MemoryRepository()
        }
    }
}