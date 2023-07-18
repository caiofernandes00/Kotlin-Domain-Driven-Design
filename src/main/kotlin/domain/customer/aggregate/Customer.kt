package domain.customer.aggregate

import domain.shared.entities.Item
import domain.shared.entities.Person
import domain.shared.entities.valueObject.Transaction
import java.util.*

class Customer private constructor(
    private val person: Person,
    private val products: List<Item>,
    private val transactions: List<Transaction>
) {

    fun getId(): UUID = person.id
    fun setId(id: UUID) = person.id == id
    fun getName(): String = person.name
    fun setName(name: String) = name.also { person.name = it }
    fun getAge(): Int? = person.age

    companion object {
        fun create(name: String): Customer {
            if (name.isEmpty()) {
                throw IllegalArgumentException("Name cannot be empty")
            }

            val person = Person(id = UUID.randomUUID(), name = name)
            return Customer(person = person, products = emptyList(), transactions = emptyList())
        }
    }
}