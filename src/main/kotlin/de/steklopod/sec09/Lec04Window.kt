package de.steklopod.sec09

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.concurrent.atomic.AtomicInteger

object Lec04Window {
    private val atomicInteger = AtomicInteger(1)

    @JvmStatic
    fun main(args: Array<String>) {
        eventStream()
            .window(3)
            .flatMap { flux: Flux<String> -> saveEvents(flux) }
            .subscribe(subscriber())
        sleepSeconds(60)
    }

    private fun eventStream(): Flux<String> = Flux.interval(Duration.ofMillis(500))
        .map { i: Long -> "🎥 event$i" }

    private fun saveEvents(flux: Flux<String>): Mono<Int> = flux
        .doOnNext { e: String -> println("🪤 saving $e") }
        .doOnComplete {
            println("saved this batch")
            println("-------------------")
        }
        .then(Mono.just(atomicInteger.getAndIncrement()))
}
