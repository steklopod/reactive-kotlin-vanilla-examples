package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono
import java.util.concurrent.Callable
import java.util.function.Supplier

object Lec05MonoFromSupplier {
    @JvmStatic
    fun main(args: Array<String>) {

        // use just only when you have data already
        // Mono<String> mono = Mono.just(getName());
        val stringSupplier = Supplier { name }
        val mono = Mono.fromSupplier(stringSupplier)
        mono.subscribe(
            Util.onNext()
        )
        val stringCallable = Callable { name }
        Mono.fromCallable(stringCallable)
            .subscribe(
                Util.onNext()
            )
    }

    private val name: String
        private get() {
            println("Generating name..")
            return Util.faker().name().fullName()
        }
}
