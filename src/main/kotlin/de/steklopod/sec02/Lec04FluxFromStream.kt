package de.steklopod.sec02

import de.steklopod.utils.Util.onComplete
import de.steklopod.utils.Util.onError
import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux

object Lec04FluxFromStream {
    @JvmStatic
    fun main(args: Array<String>) {
        val list = listOf(1, 2, 3, 4, 5)
        val stream = list.stream()

        // stream.forEach(System.out::println); // closed
        // stream.forEach(System.out::println);
        val integerFlux = Flux.fromStream { list.stream() }
        integerFlux
            .subscribe(
                onNext(),
                onError(),
                onComplete()
            )
        integerFlux
            .subscribe(
                onNext(),
                onError(),
                onComplete()
            )
    }
}
