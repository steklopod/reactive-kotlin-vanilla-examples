package de.steklopod.sec07

import de.steklopod.utils.Util.sleepMillis
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

object Lec05BufferWithSize {
    @JvmStatic
    fun main(args: Array<String>) {
        // 75% 12
        System.setProperty("reactor.bufferSize.small", "16")
        Flux.create { fluxSink: FluxSink<Int> ->
            var i = 1
            while (i < 201 && !fluxSink.isCancelled) {
                fluxSink.next(i)
                println("Pushed : $i")
                sleepMillis(1)
                i++
            }
            fluxSink.complete()
        }
            .onBackpressureBuffer(50) { o: Int -> println("Dropped : $o") }
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { i: Int? -> sleepMillis(10) }
            .subscribe(subscriber())
        sleepSeconds(10)
    }
}
