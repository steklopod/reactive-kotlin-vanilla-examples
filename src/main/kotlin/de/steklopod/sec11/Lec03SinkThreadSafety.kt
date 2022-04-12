package de.steklopod.sec11

import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.SignalType
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitResult
import java.util.concurrent.CompletableFuture

object Lec03SinkThreadSafety {
    @JvmStatic
    fun main(args: Array<String>) {
        // handle through which we would push items
        val sink = Sinks.many()
            .unicast()
            .onBackpressureBuffer<Int>()

        // handle through which subscribers will receive items
        val flux = sink.asFlux()
        val list: MutableList<Int> = ArrayList()
        flux.subscribe { e: Int -> list.add(e) }

/*        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }*/for (i in 0..999) {
            CompletableFuture.runAsync { sink.emitNext(i) { s: SignalType?, e: EmitResult? -> true } }
        }
        sleepSeconds(3)
        println(list.size)
    }
}
