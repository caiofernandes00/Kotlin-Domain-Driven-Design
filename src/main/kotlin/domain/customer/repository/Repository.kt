package domain.customer.repository

import domain.customer.aggregate.Customer
import java.util.*

class CustomerNotFoundException(
    private val customMessage: String? = null
) : Exception() {
    override val message: String
        get() = customMessage ?: "Customer not found"
}

class CustomerUpdateException(
    private val customMessage: String? = null
) : Exception() {
    override val message: String
        get() = customMessage ?: "Customer update failed"
}

class CustomerFailedToCreateException(
    private val customMessage: String? = null
) : Exception() {
    override val message: String
        get() = customMessage ?: "Customer failed to create"
}

interface CustomerRepository {
    fun getById(id: UUID): Customer
    fun update(customer: Customer): Customer
    fun create(customer: Customer): Customer
}