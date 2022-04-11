package de.steklopod.sec09.assignment

import de.steklopod.utils.Util
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Function

object OrderProcessor {
    fun automotiveProcessing(): Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> =
        Function { flux: Flux<PurchaseOrder> ->
            flux
                .doOnNext { p: PurchaseOrder -> p.price = 1.1 * p.price }
                .doOnNext { p: PurchaseOrder -> p.item = "{{ " + p.item + " }}" }
        }

    fun kidsProcessing(): Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> = Function { flux: Flux<PurchaseOrder> ->
        flux
            .doOnNext { p: PurchaseOrder -> p.price = 0.5 * p.price }
            .flatMap { p: PurchaseOrder -> Flux.concat(Mono.just(p), freeKidsOrder) }
    }

    private val freeKidsOrder: Mono<PurchaseOrder>
        get() = Mono.fromSupplier {
            PurchaseOrder(
                item = "FREE - " + Util.faker().commerce().productName(),
                price = 0.0,
                category = "Kids",
            )
        }
}
