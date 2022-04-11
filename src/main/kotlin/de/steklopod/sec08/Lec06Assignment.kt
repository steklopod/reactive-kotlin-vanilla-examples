package de.steklopod.sec08

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration

object Lec06Assignment {
    @JvmStatic
    fun main(args: Array<String>) {
        val carPrice = 10000
        Flux.combineLatest(
            monthStream(),
            demandStream()
        ) { month: Long, demand: Double -> carPrice - month * 100 * demand }
            .subscribe(subscriber())
        sleepSeconds(20)
    }

    private fun monthStream(): Flux<Long> = Flux.interval(Duration.ZERO, Duration.ofSeconds(1))

    private fun demandStream(): Flux<Double> = Flux.interval(Duration.ofSeconds(3))
        .map { i: Long? -> faker().random().nextInt(80, 120) / 100.0 }
        .startWith(1.0)
}
