package de.steklopod.sec03

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.util.*

object Lec06FluxGenerateAssignment {
    @JvmStatic
    fun main(args: Array<String>) {
        // canada
        // max = 10
        // subscriber cancels - exit
        Flux.generate { synchronousSink: SynchronousSink<Any> ->
            val country = faker().country().name()
            println("emitting $country")
            synchronousSink.next(country)
            if (country.lowercase(Locale.getDefault()) == "canada") synchronousSink.complete()
        }
            .subscribe(subscriber())
    }
}
