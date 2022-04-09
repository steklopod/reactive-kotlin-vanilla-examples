package de.steklopod.sec05.assignment

import reactor.core.publisher.Flux
import java.time.Duration
import java.util.function.Consumer

class RevenueService {
    private val db: Map<String, Double> = mapOf("Kids" to 0.0, "Automotive" to 0.0)


    fun subscribeOrderStream(): Consumer<PurchaseOrder> =
        Consumer { p: PurchaseOrder -> db[p.category]!! + p.price }

    fun revenueStream(): Flux<String> {
        return Flux.interval(Duration.ofSeconds(2))
            .map { i: Long? -> db.toString() }
    }
}
