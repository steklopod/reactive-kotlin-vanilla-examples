package de.steklopod.sec04

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec07Timeout {
    @JvmStatic
    fun main(args: Array<String>) {
        orderNumbers
            .timeout(Duration.ofSeconds(2), fallback())
            .subscribe(subscriber())
        sleepSeconds(60)
    }

    private val orderNumbers: Flux<Int> = Flux.range(1, 10)
        .delayElements(Duration.ofSeconds(5))

    private fun fallback(): Flux<Int> = Flux.range(100, 10)
        .delayElements(Duration.ofSeconds(5))
}
