package de.steklopod.sec05.assignment

import reactor.core.publisher.Flux
import java.time.Duration
import java.util.function.Consumer

class InventoryService {
    private val db: Map<String, Int> = mapOf("Kids" to 100, "Automotive" to 100)


    fun subscribeOrderStream(): Consumer<PurchaseOrder> = Consumer { p: PurchaseOrder ->
        db[p.category]!! - p.quantity
    }

    fun inventoryStream(): Flux<String> = Flux.interval(Duration.ofSeconds(2))
        .map { i: Long? -> db.toString() }
}
