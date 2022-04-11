package de.steklopod.sec11

import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Sinks

object Lec01SinkOne {
    @JvmStatic
    fun main(args: Array<String>) {

        // mono 1 value / empty / error
        val sink = Sinks.one<String>()
        val mono = sink.asMono()

        mono.subscribe(subscriber("sam"))
        mono.subscribe(subscriber("mike"))

/*        sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });

        sink.emitValue("hello", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });
        */
        sink.tryEmitValue("Hello")
    }
}
