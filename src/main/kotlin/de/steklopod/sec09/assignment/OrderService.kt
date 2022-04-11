package de.steklopod.sec09.assignment

import reactor.core.publisher.Flux
import java.time.Duration

object OrderService {
    fun orderStream(): Flux<PurchaseOrder> = Flux.interval(Duration.ofMillis(100))
        .map { PurchaseOrder() }
}
