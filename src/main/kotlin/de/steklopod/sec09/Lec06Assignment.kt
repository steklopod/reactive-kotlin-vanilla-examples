package de.steklopod.sec09

import de.steklopod.sec09.assignment.OrderProcessor
import de.steklopod.sec09.assignment.OrderService
import de.steklopod.sec09.assignment.PurchaseOrder
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.GroupedFlux

object Lec06Assignment {

    private val map = mapOf(
        "Kids" to OrderProcessor.kidsProcessing(),
        "Automotive" to OrderProcessor.automotiveProcessing()
    )

    @JvmStatic
    fun main(args: Array<String>) {
        val keys: Set<String> = map.keys

        OrderService.orderStream()
            .filter { p: PurchaseOrder -> keys.contains(p.category) }
            .groupBy { obj: PurchaseOrder -> obj.category } // 2 keys
            .flatMap { gf: GroupedFlux<String, PurchaseOrder> ->
                map[gf.key()]!!
                    .apply(gf)
            } //flux
            .subscribe(subscriber())

        sleepSeconds(60)
    }
}
