package de.steklopod.sec02.assignment

import de.steklopod.utils.Util.faker
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger

object StockPricePublisher {
    val price: Flux<Int?>
        get() {
            val atomicInteger = AtomicInteger(100)
            return Flux.interval(Duration.ofSeconds(1))
                .map { i: Long? ->
                    atomicInteger.getAndAccumulate(
                        faker().random().nextInt(-5, 5)
                    ) { a: Int, b: Int -> Integer.sum(a, b) }
                }
        }
}
