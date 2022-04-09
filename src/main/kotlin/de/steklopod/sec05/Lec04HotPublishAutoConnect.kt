package de.steklopod.sec05

import de.steklopod.utils.Util.sleepSeconds
import de.steklopod.utils.Util.subscriber
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.stream.Stream

object Lec04HotPublishAutoConnect {
    @JvmStatic
    fun main(args: Array<String>) {
        // share = publish().refCount(1)
        val movieStream = Flux.fromStream { movie }
            .delayElements(Duration.ofSeconds(1))
            .publish()
            .autoConnect(0)
        sleepSeconds(3)
        movieStream
            .subscribe(subscriber("sam"))
        sleepSeconds(10)
        println("Mike is about to join")
        movieStream
            .subscribe(subscriber("mike"))
        sleepSeconds(60)
    }

    // movie-theatre
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
