package de.steklopod.sec08

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec05CombineLatest {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.combineLatest(string, number) { s: String, i: Int -> s + i }
            .subscribe(subscriber())
        sleepSeconds(10)
    }

    private val string: Flux<String>
        private get() = Flux.just("A", "B", "C", "D")
            .delayElements(Duration.ofSeconds(1))
    private val number: Flux<Int>
        private get() = Flux.just(1, 2, 3)
            .delayElements(Duration.ofSeconds(3))
}
