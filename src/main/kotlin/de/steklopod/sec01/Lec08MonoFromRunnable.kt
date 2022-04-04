package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono

object Lec08MonoFromRunnable {
    @JvmStatic
    fun main(args: Array<String>) {
        Mono.fromRunnable<Any>(timeConsumingProcess())
            .subscribe(
                Util.onNext(),
                Util.onError()
            ) { println("process is done. Sending emails...") }
    }

    private fun timeConsumingProcess(): Runnable {
        return Runnable {
            Util.sleepSeconds(3)
            println("Operation completed")
        }
    }
}
