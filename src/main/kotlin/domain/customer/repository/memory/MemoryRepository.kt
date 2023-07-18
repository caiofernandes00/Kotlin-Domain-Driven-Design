package domain.customer.repository.memory

import domain.customer.aggregate.Customer
import domain.customer.repository.CustomerFailedToCreateException
import domain.customer.repository.CustomerNotFoundException
import domain.customer.repository.CustomerRepository
import java.util.*

class MemoryRepository private constructor(
    private val customersStorage: MutableMap<UUID, Customer> = mutableMapOf()
) : CustomerRepository {

    override fun create(customer: Customer): Customer {
        synchronized(customersStorage) {
            if (customersStorage.containsKey(customer.getId())) {
                throw CustomerFailedToCreateException()
            }

            customersStorage[customer.getId()] = customer
        }

        return customer
    }

    @Throws(CustomerNotFoundException::class)
    override fun getById(id: UUID): Customer {
        if (customersStorage.containsKey(id)) {
            return customersStorage[id]!!
        }

        throw CustomerNotFoundException()
    }

    override fun update(customer: Customer): Customer {
        TODO("Not yet implemented")
    }

    companion object {
        fun create(): MemoryRepository {
            return MemoryRepository()
        }
    }
}