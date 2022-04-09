package de.steklopod.sec05

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

object Lec01ColdPublisher {
    @JvmStatic
    fun main(args: Array<String>) {
        val movieStream = Flux.fromStream { movie }
            .delayElements(Duration.ofSeconds(2))

        movieStream
            .subscribe(subscriber("sam"))
        sleepSeconds(5)

        movieStream
            .subscribe(subscriber("mike"))
        sleepSeconds(60)
    }

    // netflix
    private val movie: Stream<String>
        get() {
            println("Got the movie streaming req")
            return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
            )
        }
}
