package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

object Lec07MonoFromFuture {
    @JvmStatic
    fun main(args: Array<String>) {
        Mono.fromFuture(name)
            .subscribe(Util.onNext())
        Util.sleepSeconds(1)
    }

    private val name: CompletableFuture<String> = CompletableFuture.supplyAsync { Util.faker().name().fullName() }
}
