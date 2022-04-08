package de.steklopod.sec02

import de.steklopod.utils.Util.sleepSeconds
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import reactor.core.publisher.Flux
import java.util.concurrent.atomic.AtomicReference

object Lec06Subscription {
    @JvmStatic
    fun main(args: Array<String>) {
        val atomicReference = AtomicReference<Subscription>()
        Flux.range(1, 20)
            .log()
            .subscribeWith(object : Subscriber<Int> {
                override fun onSubscribe(subscription: Subscription) {
                    println("Received Sub : $subscription")
                    atomicReference.set(subscription)
                }

                override fun onNext(integer: Int) {
                    println("onNext : $integer")
                }

                override fun onError(throwable: Throwable) {
                    println("onError : " + throwable.message)
                }

                override fun onComplete() {
                    println("onComplete")
                }
            })
        sleepSeconds(3)
        atomicReference.get().request(3)
        sleepSeconds(5)
        atomicReference.get().request(3)
        sleepSeconds(5)
        println("going to cancel")
        atomicReference.get().cancel()
        sleepSeconds(3)
        atomicReference.get().request(4)
        sleepSeconds(3)
    }
}
