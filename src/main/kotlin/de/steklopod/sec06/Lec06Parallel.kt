package de.steklopod.sec06

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

object Lec06Parallel {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(1, 10)
            .parallel(10)
            .runOn(Schedulers.boundedElastic())
            .doOnNext { i: Int -> printThreadName("next $i") }
            .sequential()
            .subscribe { v: Int -> printThreadName("sub $v") }

        sleepSeconds(5)
    }

    private fun printThreadName(msg: String) {
        println(msg + "\t\t: Thread : " + Thread.currentThread().name)
    }
}
