package de.steklopod.sec04

import de.steklopod.sec04.helper.Person
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.util.*
import java.util.function.Function

object Lec10Transform {
    @JvmStatic
    fun main(args: Array<String>) {
        person
            .transform(applyFilterMap())
            .subscribe(subscriber())
    }

    private val person: Flux<Person> = Flux.range(1, 10)
        .map { Person() }

    private fun applyFilterMap(): Function<Flux<Person>, Flux<Person>> = Function { flux: Flux<Person> ->
        flux
            .filter { p: Person -> p.age > 10 }
            .doOnNext { p: Person -> p.name = p.name.uppercase(Locale.getDefault()) }
            .doOnDiscard(Person::class.java) { p: Person -> println("Not allowing : $p") }
    }
}
