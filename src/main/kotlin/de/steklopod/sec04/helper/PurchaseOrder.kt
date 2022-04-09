package de.steklopod.sec04.helper

import de.steklopod.utils.Util.faker
import lombok.Data
import lombok.ToString

@Data
@ToString
class PurchaseOrder(private val userId: Int) {
    private val item: String
    private val price: String

    init {
        item = faker().commerce().productName()
        price = faker().commerce().price()
    }
}
