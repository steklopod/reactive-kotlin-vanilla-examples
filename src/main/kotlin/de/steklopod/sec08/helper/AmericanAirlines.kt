package de.steklopod.sec08.helper

import de.steklopod.utils.Util.faker
import reactor.core.publisher.Flux
import java.time.Duration

object AmericanAirlines {
    val flights: Flux<String>
        get() = Flux.range(1, faker().random().nextInt(1, 10))
            .delayElements(Duration.ofSeconds(1))
            .map { "AA " + faker().random().nextInt(100, 999) }
            .filter { faker().random().nextBoolean() }
}
