package de.steklopod.sec09.assignment

import de.steklopod.utils.Util.faker

data class PurchaseOrder(
    var item: String = faker().commerce().productName(),
    var price: Double = faker().commerce().price().replace(",",".").toDouble(),
    var category: String = faker().commerce().department()
)
