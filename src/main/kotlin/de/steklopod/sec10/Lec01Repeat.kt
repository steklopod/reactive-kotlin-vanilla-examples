package de.steklopod.sec10

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.util.concurrent.atomic.AtomicInteger

object Lec01Repeat {
    private val atomicInteger = AtomicInteger(1)

    @JvmStatic
    fun main(args: Array<String>) {
        integers()
            .repeat { atomicInteger.get() < 14 }
            .subscribe(subscriber())
    }

    private fun integers(): Flux<Int> = Flux.range(1, 3)
            .doOnSubscribe { println("Subscribed") }
            .doOnComplete { println("--Completed") }
            .map { atomicInteger.getAndIncrement() }
}
