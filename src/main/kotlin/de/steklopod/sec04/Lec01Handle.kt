package de.steklopod.sec04

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink

object Lec01Handle {
    @JvmStatic
    fun main(args: Array<String>) {

        // handle = filter + map
        Flux.range(1, 20)
            .handle { integer: Int, synchronousSink: SynchronousSink<Any> ->
                if (integer == 7) synchronousSink.complete()
                else synchronousSink.next(integer)
            }
            .subscribe(subscriber())
    }
}
