package de.steklopod.sec03

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.util.*

object Lec07FluxGenerateCounter {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.generate(
            { 1 }
        ) { counter: Int, sink: SynchronousSink<Any> ->
            val country = faker().country().name()
            sink.next(country)
            if (counter >= 10 || country.lowercase(Locale.getDefault()) == "canada") sink.complete()
            counter + 1
        }
            .take(4)
            .subscribe(subscriber())
    }
}
