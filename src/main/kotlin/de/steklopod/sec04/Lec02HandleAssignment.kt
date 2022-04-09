package de.steklopod.sec04

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.util.*

object Lec02HandleAssignment {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.generate { synchronousSink: SynchronousSink<String> -> synchronousSink.next(faker().country().name()) }
            .handle { s: String, synchronousSink: SynchronousSink<String> ->
                synchronousSink.next(s)
                if (s.lowercase(Locale.getDefault()) == "canada") synchronousSink.complete()
            }
            .subscribe(subscriber())
    }
}
