package de.steklopod.sec04

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

object Lec06OnError {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 10)
            .log()
            .map { i: Int -> 10 / (5 - i) } // .onErrorReturn(-1)
            //  .onErrorResume(e -> fallback())
            .onErrorContinue { err: Throwable?, obj: Any? -> }
            .subscribe(subscriber())
    }

    private fun fallback(): Mono<Int> = Mono.fromSupplier { faker().random().nextInt(100, 200) }
}
