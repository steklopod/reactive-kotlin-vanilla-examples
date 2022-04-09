package de.steklopod.sec05.assignment

import de.steklopod.utils.Util.faker
import lombok.Data
import lombok.ToString


data class PurchaseOrder(
    val item: String = faker().commerce().productName(),
    val price: Double = faker().commerce().price().toDouble(),
    val category: String = faker().commerce().department(),
    val quantity: Int = faker().random().nextInt(1, 10)
)
