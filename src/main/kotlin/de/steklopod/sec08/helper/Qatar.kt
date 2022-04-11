package de.steklopod.sec08.helper

import de.steklopod.utils.Util.faker
import reactor.core.publisher.Flux
import java.time.Duration

object Qatar {
    val flights: Flux<String>
        get() = Flux.range(1, faker().random().nextInt(1, 5))
            .delayElements(Duration.ofSeconds(1))
            .map { "Qatar " + faker().random().nextInt(100, 999) }
            .filter { faker().random().nextBoolean() }
}
