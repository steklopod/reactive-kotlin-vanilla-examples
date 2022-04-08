package de.steklopod.sec02

import de.steklopod.utils.Util.onComplete
import de.steklopod.utils.Util.onError
import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux

object Lec09FluxFromMono {
    @JvmStatic
    fun main(args: Array<String>) {

        /*       Mono<String> mono = Mono.just("a");

        Flux<String> flux = Flux.from(mono);

        flux.subscribe(Util.onNext());*/
        Flux.range(1, 10)
            .next() // 1
            .filter { i: Int -> i > 3 }
            .subscribe(onNext(), onError(), onComplete())
    }

    private fun doSomething(flux: Flux<String>) {}
}
