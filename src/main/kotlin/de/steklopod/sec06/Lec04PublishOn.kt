package de.steklopod.sec06

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

object Lec04PublishOn {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Flux.create { fluxSink: FluxSink<Any> ->
            printThreadName("create")
            for (i in 0..3) {
                fluxSink.next(i)
            }
            fluxSink.complete()
        }
            .doOnNext { i: Any -> printThreadName("next $i") }

        flux
            .publishOn(Schedulers.boundedElastic())
            .doOnNext { i: Any -> printThreadName("next $i") }
            .publishOn(Schedulers.parallel())
            .subscribe { v: Any -> printThreadName("sub $v") }

        sleepSeconds(5)
    }

    private fun printThreadName(msg: String) {
        println(msg + "\t\t: Thread : " + Thread.currentThread().name)
    }
}
