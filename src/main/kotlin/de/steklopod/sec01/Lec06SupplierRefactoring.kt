package de.steklopod.sec01

import de.steklopod.utils.Util
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.util.*

object Lec06SupplierRefactoring {
    @JvmStatic
    fun main(args: Array<String>) {
        name

        val nameGenerated: String? = name
            .subscribeOn(Schedulers.boundedElastic())
            .block()
        println(nameGenerated)

        name
        Util.sleepSeconds(4)
    }

    private val name: Mono<String>
        get() {
            println("entered getName method")
            return Mono.fromSupplier {
                println("Generating name..")
                Util.sleepSeconds(3)
                Util.faker().name().fullName()
            }.map { obj: String -> obj.uppercase(Locale.getDefault()) }
        }
}
