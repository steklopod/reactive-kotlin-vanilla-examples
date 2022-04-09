package de.steklopod.sec04

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec04LimitRate {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 1000) // 175
            .log()
            .limitRate(100, 0) // 75%
            .subscribe(subscriber())
    }
}
