package de.steklopod.sec06

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.scheduler.Schedulers

object Lec03SubscribeOnMultipleItems {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Flux.create { fluxSink: FluxSink<Any> ->
            printThreadName("create")
            for (i in 0..3) {
                fluxSink.next(i)
                sleepSeconds(1)
            }
            fluxSink.complete()
        }
            .doOnNext { i: Any -> printThreadName("next $i") }

        flux
            .subscribeOn(Schedulers.boundedElastic())
            .subscribe { v: Any -> printThreadName("sub $v") }

        flux
            .subscribeOn(Schedulers.parallel())
            .subscribe { v: Any -> printThreadName("sub $v") }

        sleepSeconds(5)
    }

    private fun printThreadName(msg: String) {
        println(msg + "\t\t: Thread : " + Thread.currentThread().name)
    }
}
