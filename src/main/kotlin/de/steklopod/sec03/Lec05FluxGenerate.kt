package de.steklopod.sec03

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink

object Lec05FluxGenerate {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.generate { synchronousSink: SynchronousSink<Any> ->
            println("emitting")
            synchronousSink.next(faker().country().name()) // 1
        }
            .take(2)
            .subscribe(subscriber())
    }
}
