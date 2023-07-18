package domain.product.aggregate

import domain.shared.entities.Item
import java.util.*

class Product private constructor(
    val item: Item,
    val price: Float,
    val quantity: Int
) {

    fun getId(): UUID = item.id

    companion object {
        fun create(name: String, description: String, price: Float): Product {
            validate(name, description)
            return Product(
                item = Item(UUID.randomUUID(), name, description),
                price = price,
                quantity = 0
            )
        }

        private fun validate(name: String, description: String) {
            if (name.isEmpty() || description.isEmpty()) {
                throw IllegalArgumentException("Missing required fields")
            }
        }
    }
}