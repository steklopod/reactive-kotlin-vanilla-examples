package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec02SinkUnicast {
    @JvmStatic
    fun main(args: Array<String>) {

        // handle through which we would push items
        val sink = Sinks.many().unicast().onBackpressureBuffer<String>()

        // handle through which subscribers will receive items
        val flux = sink.asFlux()
        flux.subscribe(subscriber("sam"))
        flux.subscribe(subscriber("mike"))
        sink.tryEmitNext("hi")
        sink.tryEmitNext("how are you")
        sink.tryEmitNext("?")
    }
}
