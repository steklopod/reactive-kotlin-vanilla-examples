package de.steklopod.sec05

import de.steklopod.sec05.assignment.InventoryService
import de.steklopod.sec05.assignment.OrderService
import de.steklopod.sec05.assignment.RevenueService
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber

object Lec06Assignment {
    @JvmStatic
    fun main(args: Array<String>) {
        val orderService = OrderService()
        val revenueService = RevenueService()
        val inventoryService = InventoryService()

        // revenue and inv - observe the order stream
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream())
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream())

        inventoryService.inventoryStream()
            .subscribe(subscriber("inventory"))

        revenueService.revenueStream()
            .subscribe(subscriber("revenue"))

        sleepSeconds(60)
    }
}
