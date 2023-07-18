package domain.shared.entities

import java.util.UUID

data class Item(
    val id: UUID,
    val name: String,
    val description: String
)