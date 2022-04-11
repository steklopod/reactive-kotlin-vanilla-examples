package de.steklopod.sec08

import de.steklopod.sec08.helper.AmericanAirlines
import de.steklopod.sec08.helper.Emirates
import de.steklopod.sec08.helper.Qatar
import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux

object Lec03Merge {
    @JvmStatic
    fun main(args: Array<String>) {
        val merge = Flux.merge(
            Qatar.flights,
            Emirates.flights,
            AmericanAirlines.flights
        )
        merge.subscribe(subscriber())
        sleepSeconds(10)
    }
}
