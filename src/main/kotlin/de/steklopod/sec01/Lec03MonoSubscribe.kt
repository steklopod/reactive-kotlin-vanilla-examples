package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono

object Lec03MonoSubscribe {
    @JvmStatic
    fun main(args: Array<String>) {

        // publisher
        val mono = Mono.just("ball")
            .map { obj: String -> obj.length }
            .map { l: Int -> l / 1 }

        // 1
        // mono.subscribe();

        // 2
        mono.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onComplete()
        )
    }
}
