package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec06SinkReplay {
    @JvmStatic
    fun main(args: Array<String>) {
        // handle through which we would push items
        val sink = Sinks.many()
            .replay()
            .all<String>()

        // handle through which subscribers will receive items
        val flux = sink.asFlux()

        sink.tryEmitNext("ððŧ hi")
        sink.tryEmitNext("ð how are you")

        flux.subscribe(subscriber("sam"))
        flux.subscribe(subscriber("mike"))

        sink.tryEmitNext("ðŠĪ???")

        flux.subscribe(subscriber("jake"))

        sink.tryEmitNext("ðĨ new msg")
    }
}
