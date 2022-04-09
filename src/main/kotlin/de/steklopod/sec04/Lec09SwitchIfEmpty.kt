package de.steklopod.sec04

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec09SwitchIfEmpty {
    @JvmStatic
    fun main(args: Array<String>) {
        orderNumbers
            .filter { i: Int -> i > 10 }
            .switchIfEmpty(fallback())
            .subscribe(subscriber())
    }

    // redis cache / db
    private val orderNumbers: Flux<Int> = Flux.range(1, 10)

    // db // cache
    private fun fallback(): Flux<Int> = Flux.range(20, 5)
}
