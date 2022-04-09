package de.steklopod.sec06

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

object Lec01ThreadDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Flux.create { fluxSink: FluxSink<Any> ->
            printThreadName("create")
            fluxSink.next(1)
        }
            .doOnNext { i: Any -> printThreadName("next $i") }
        val runnable = Runnable { flux.subscribe { v: Any -> printThreadName("sub $v") } }
        for (i in 0..1) {
            Thread(runnable).start()
        }
        sleepSeconds(5)
    }

    private fun printThreadName(msg: String) {
        println(msg + "\t\t: Thread : " + Thread.currentThread().name)
    }
}
