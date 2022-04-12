package de.steklopod.sec04.helper

import de.steklopod.utils.Util.faker

data class PurchaseOrder(
    val userId: Int,
    val item: String = faker().commerce().productName(),
    val price: String = faker().commerce().price()
)
