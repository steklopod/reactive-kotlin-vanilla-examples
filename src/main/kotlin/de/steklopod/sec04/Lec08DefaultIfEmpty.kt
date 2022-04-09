package de.steklopod.sec04

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec08DefaultIfEmpty {
    @JvmStatic
    fun main(args: Array<String>) {
        orderNumbers
            .filter { i: Int -> i > 10 }
            .defaultIfEmpty(-100)
            .subscribe(subscriber())
    }

    private val orderNumbers: Flux<Int> = Flux.range(1, 12)
}
