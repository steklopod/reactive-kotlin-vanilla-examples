package de.steklopod.sec07

import de.steklopod.utils.Util.sleepMillis
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

object Lec01Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.create { fluxSink: FluxSink<Int> ->
            for (i in 1..500) {
                fluxSink.next(i)
                println("Pushed : $i")
            }
            fluxSink.complete()
        }
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { i: Int -> sleepMillis(10) }
            .subscribe(subscriber())
        sleepSeconds(60)
    }
}
