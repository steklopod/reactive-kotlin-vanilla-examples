package de.steklopod.sec03

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.*

object Lec04FluxCreateIssueFix {
    @JvmStatic
    fun main(args: Array<String>) {

        // only one instance of flux sink
        Flux.create { fluxSink: FluxSink<String> ->
            var country: String
            var counter = 0
            do {
                country = faker().country().name()
                println("emitting : $country")
                fluxSink.next(country)
                counter++
            } while (country.lowercase(Locale.getDefault()) != "canada"
                && !fluxSink.isCancelled && counter < 10
            )
            fluxSink.complete()
        }
            .take(3)
            .subscribe(subscriber())
    }
}
