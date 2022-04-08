package de.steklopod.sec03

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import java.util.*

object Lec01FluxCreate {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.create { fluxSink: FluxSink<String> ->
            var country: String
            do {
                country = faker().country().name()
                fluxSink.next(country)
            } while (country.lowercase(Locale.getDefault()) != "canada")
            fluxSink.complete()
        }
            .subscribe(subscriber())
    }
}
