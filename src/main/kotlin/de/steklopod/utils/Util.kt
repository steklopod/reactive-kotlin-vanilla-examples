package de.steklopod.utils

import com.github.javafaker.Faker
import org.reactivestreams.Subscriber
import java.util.function.Consumer

object Util {
    private val FAKER = Faker.instance()
    @JvmStatic
    fun onNext(): Consumer<Any> {
        return Consumer { o: Any -> println("Received : $o") }
    }

    @JvmStatic
    fun onError(): Consumer<Throwable> {
        return Consumer { e: Throwable -> System.err.println("ERROR : " + e.message) }
    }

    @JvmStatic
    fun onComplete(): Runnable {
        return Runnable { println("Completed") }
    }

    @JvmStatic
    fun faker(): Faker {
        return FAKER
    }

    @JvmStatic
    fun sleepSeconds(seconds: Int) {
        sleepMillis(seconds * 1000)
    }

    @JvmStatic
    fun sleepMillis(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
    @JvmStatic
    fun subscriber(): Subscriber<Any> {
        return DefaultSubscriber()
    }

    @JvmStatic
    fun subscriber(name: String): Subscriber<Any> {
        return DefaultSubscriber(name)
    }
}
