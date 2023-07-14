package services

import domain.customer.CustomerRepository
import domain.customer.memory.MemoryRepository

typealias OrderConfiguration = (OrderService.() -> Unit)
class OrderService {
    private lateinit var customers: CustomerRepository

    companion object {
        fun create(vararg configuration: OrderConfiguration): OrderService {
            val orderService = OrderService()
            for (config in configuration) {
                config.invoke(orderService)
            }
            return orderService
        }

        fun withCustomerRepository(customers: CustomerRepository): OrderConfiguration {
            return {
                this.customers = customers
            }
        }
    }
}