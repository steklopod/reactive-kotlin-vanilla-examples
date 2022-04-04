package de.steklopod.utils

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class DefaultSubscriber : Subscriber<Any> {
    private var name = ""

    constructor(name: String) {
        this.name = "$name - "
    }

    constructor() {}

    override fun onSubscribe(subscription: Subscription) {
        subscription.request(Long.MAX_VALUE)
    }

    override fun onNext(o: Any) {
        println(name + "Received : " + o)
    }

    override fun onError(throwable: Throwable) {
        println(name + "ERROR : " + throwable.message)
    }

    override fun onComplete() {
        println(name + "Completed")
    }
}
