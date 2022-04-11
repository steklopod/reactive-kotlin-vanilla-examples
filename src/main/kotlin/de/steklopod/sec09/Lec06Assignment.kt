package de.steklopod.sec09

import de.steklopod.sec09.assignment.OrderProcessor
import de.steklopod.sec09.assignment.OrderService
import de.steklopod.sec09.assignment.PurchaseOrder
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.GroupedFlux
import java.util.Map

object Lec06Assignment {
    @JvmStatic
    fun main(args: Array<String>) {
        val map = Map.of(
            "Kids", OrderProcessor.kidsProcessing(),
            "Automotive", OrderProcessor.automotiveProcessing()
        )
        val set: Set<String> = map.keys
        OrderService.orderStream()
            .filter { p: PurchaseOrder -> set.contains(p.category) }
            .groupBy { obj: PurchaseOrder -> obj.category } // 2 keys
            .flatMap { gf: GroupedFlux<String, PurchaseOrder> ->
                map[gf.key()]!!
                    .apply(gf)
            } //flux
            .subscribe(subscriber())
        sleepSeconds(60)
    }
}
