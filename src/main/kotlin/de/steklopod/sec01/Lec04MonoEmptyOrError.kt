package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono

object Lec04MonoEmptyOrError {

    @JvmStatic
    fun main(args: Array<String>) {
        userRepository(1)
            .subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
            )
    }

    private fun userRepository(userId: Int): Mono<String> {
        // 1
        return when (userId) {
            1 -> Mono.just(Util.faker().name().firstName())
            2 -> Mono.empty() // null
            else -> Mono.error(RuntimeException("Not in the allowed range"))
        }
    }
}
