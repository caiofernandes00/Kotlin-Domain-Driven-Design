package entities.valueObject

import java.time.ZonedDateTime
import java.util.*

data class Transaction(
    val amount: Int,
    val from: UUID,
    val to: UUID,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime
)