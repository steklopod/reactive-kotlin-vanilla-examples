package de.steklopod.sec08.helper

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink

class NameGenerator {
    private val list: MutableList<String> = ArrayList()

    fun generateNames(): Flux<String> = Flux.generate { stringSynchronousSink: SynchronousSink<String> ->
        println("generated fresh")

        sleepSeconds(1)

        val name = faker().name().firstName()

        list.add(name)

        stringSynchronousSink.next(name)
    }
//        .cast(String::class.java)
        .startWith(fromCache)

    private val fromCache: Flux<String>
        get() = Flux.fromIterable(list)
}
