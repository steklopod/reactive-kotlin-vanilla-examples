package de.steklopod.sec03.helper

import de.steklopod.utils.Util.faker
import reactor.core.publisher.FluxSink
import java.util.function.Consumer

class NameProducer : Consumer<FluxSink<String>?> {
    private var fluxSink: FluxSink<String>? = null

    override fun accept(stringFluxSink: FluxSink<String>?) {
        fluxSink = stringFluxSink
    }

    fun produce() {
        val name = faker().name().fullName()
        val thread = Thread.currentThread().name
        fluxSink!!.next("$thread : $name")
    }
}
