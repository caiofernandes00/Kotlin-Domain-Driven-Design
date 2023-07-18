package services

import domain.customer.repository.CustomerRepository
import domain.product.aggregate.Product
import domain.product.repository.ProductRepository
import java.util.*
import domain.customer.repository.memory.MemoryRepository as CustomerMemoryRepository
import domain.product.repository.memory.MemoryRepository as ProductMemoryRepository

typealias OrderConfiguration = (OrderService.() -> Unit)

class OrderService private constructor() {
    private lateinit var customersRepository: CustomerRepository
    private lateinit var productsRepository: ProductRepository
    private lateinit var billingService: Any

    fun create(customerId: UUID, productIds: List<UUID>) {
        val customer = customersRepository.getById(customerId)
        val products = mutableListOf<Product>()
        var price = 0f

        for (productId in productIds) {
            val product = productsRepository.getById(productId)
            products.add(product)
            price += product.price
        }
        return
    }

    companion object {
        fun create(vararg configuration: OrderConfiguration): OrderService {
            val orderService = OrderService()
            for (config in configuration) {
                config.invoke(orderService)
            }
            return orderService
        }

        fun withCustomerRepository(customersRepository: CustomerRepository?): OrderConfiguration {
            return {
                this.customersRepository = customersRepository ?: CustomerMemoryRepository.create()
            }
        }

        fun withProductsRepository(
            productsRepository: ProductRepository?,
            products: List<Product>
        ): OrderConfiguration {
            val repo = productsRepository ?: ProductMemoryRepository.create()
            for (product in products) {
                repo.create(product)
            }

            return {
                this.productsRepository = productsRepository ?: repo
            }
        }
    }
}