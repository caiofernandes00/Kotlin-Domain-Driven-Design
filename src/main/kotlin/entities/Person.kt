package entities

import java.util.UUID

data class Person(
    val id: UUID,
    var name: String,
    val age: Int? = null
)