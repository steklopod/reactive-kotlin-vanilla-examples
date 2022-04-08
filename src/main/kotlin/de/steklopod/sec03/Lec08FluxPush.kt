package de.steklopod.sec03

import de.steklopod.sec03.helper.NameProducer
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec08FluxPush {
    @JvmStatic
    fun main(args: Array<String>) {
        val nameProducer = NameProducer()
        Flux.create(nameProducer)
            .subscribe(subscriber())
        val runnable = Runnable { nameProducer.produce() }
        for (i in 0..9) {
            Thread(runnable).start()
        }
        sleepSeconds(2)
    }
}
