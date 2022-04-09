package de.steklopod.sec05.assignment

import reactor.core.publisher.Flux
import java.time.Duration
import java.util.*

class OrderService {
    private var flux: Flux<PurchaseOrder>? = null

    fun orderStream(): Flux<PurchaseOrder> {
        if (Objects.isNull(flux)) flux = orderStream
        return flux!!
    }

    private val orderStream: Flux<PurchaseOrder> = Flux.interval(Duration.ofMillis(100))
        .map { i: Long? -> PurchaseOrder() }
        .publish()
        .refCount(2)
}
