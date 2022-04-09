package de.steklopod.sec04

import de.steklopod.sec04.helper.Person
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import reactor.core.publisher.Signal
import java.util.*
import java.util.function.Function

object Lec11SwitchOnFirst {
    @JvmStatic
    fun main(args: Array<String>) {
        person
            .switchOnFirst { signal: Signal<out Person>, personFlux: Flux<Person> ->
                println("inside switch-on-first")
                if (signal.isOnNext && signal.get()!!.age > 10) personFlux
                else applyFilterMap().apply(personFlux)
            }
            .subscribe(subscriber())
    }

    private val person: Flux<Person> = Flux.range(1, 10)
        .map { i: Int -> Person() }

    private fun applyFilterMap(): Function<Flux<Person>, Flux<Person>> = Function { flux: Flux<Person> ->
        flux
            .filter { p: Person -> p.age > 10 }
            .doOnNext { p: Person -> p.name = p.name.uppercase(Locale.getDefault()) }
            .doOnDiscard(Person::class.java) { p: Person -> println("Not allowing : $p") }
    }
}
