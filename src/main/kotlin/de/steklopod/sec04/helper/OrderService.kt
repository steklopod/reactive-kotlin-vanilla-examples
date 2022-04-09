package de.steklopod.sec04.helper

import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.time.Duration
import java.util.function.Consumer

object OrderService {

    private val list1 = listOf(
        PurchaseOrder(1),
        PurchaseOrder(1),
        PurchaseOrder(1)
    )
    private val list2 = listOf(
        PurchaseOrder(2),
        PurchaseOrder(2)
    )
    private val db: Map<Int, List<PurchaseOrder>> = mapOf(1 to list1, 2 to list2)


    fun getOrders(userId: Int): Flux<PurchaseOrder> = Flux.create { purchaseOrderFluxSink: FluxSink<PurchaseOrder> ->
        db[userId]!!
            .forEach(
                Consumer { t: PurchaseOrder -> purchaseOrderFluxSink.next(t) })
        purchaseOrderFluxSink.complete()
    }
        .delayElements(Duration.ofSeconds(1))
}
