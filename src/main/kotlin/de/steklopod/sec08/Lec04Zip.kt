package de.steklopod.sec08

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec04Zip {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.zip(body, engine, tires)
            .subscribe(subscriber())
    }

    private val body: Flux<String>
        private get() = Flux.range(1, 5)
            .map { i: Int? -> "body" }
    private val engine: Flux<String>
        private get() = Flux.range(1, 3)
            .map { i: Int? -> "engine" }
    private val tires: Flux<String>
        private get() = Flux.range(1, 6)
            .map { i: Int? -> "tires" }
}
