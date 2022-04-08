package de.steklopod.utils

import com.github.javafaker.Faker
import org.reactivestreams.Subscriber
import java.util.function.Consumer

object Util {
    private val FAKER = Faker.instance()

    @JvmStatic
    fun onNext(): Consumer<Any> = Consumer { o: Any -> println("\t👯️ Received onNEXT : $o") }

    @JvmStatic
    fun onError(): Consumer<Throwable> = Consumer { e: Throwable ->
        System.err.println("\tERROR : " + e.message)
    }

    @JvmStatic
    fun onComplete(): Runnable = Runnable { println("\t🏁 onCompleted") }

    @JvmStatic
    fun faker(): Faker = FAKER

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
    fun subscriber(): Subscriber<Any> = DefaultSubscriber()

    @JvmStatic
    fun subscriber(name: String): Subscriber<Any> = DefaultSubscriber(name)
}
