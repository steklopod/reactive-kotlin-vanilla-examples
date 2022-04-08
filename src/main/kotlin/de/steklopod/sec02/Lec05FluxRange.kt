package de.steklopod.sec02

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux

object Lec05FluxRange {
    @JvmStatic
    fun main(args: Array<String>) {
        Flux.range(3, 10)
            .log()
            .map { faker().name().fullName() }
            .log()
            .subscribe(
                onNext()
            )
    }
}
