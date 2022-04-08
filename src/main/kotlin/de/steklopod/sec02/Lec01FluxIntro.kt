package de.steklopod.sec02

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.onComplete
import de.steklopod.utils.Util.onError
import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux

object Lec01FluxIntro {
    @JvmStatic
    fun main(args: Array<String>) {
        val flux = Flux.just<Any>(1, 2, 3, "a", faker().name().fullName())
        flux.subscribe(
            onNext(),
            onError(),
            onComplete()
        )
    }
}
