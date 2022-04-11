package de.steklopod.sec07

import de.steklopod.utils.Util.sleepMillis
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

object Lec03Latest {
    @JvmStatic
    fun main(args: Array<String>) {
        // 75% 12
        System.setProperty("reactor.bufferSize.small", "16")
        Flux.create { fluxSink: FluxSink<Int> ->
            for (i in 1..200) {
                fluxSink.next(i)
                println("Pushed : $i")
                sleepMillis(1)
            }
            fluxSink.complete()
        }
            .onBackpressureLatest()
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { i: Int -> sleepMillis(10) }
            .subscribe(subscriber())
        sleepSeconds(10)
    }
}
