package de.steklopod.sec02

import de.steklopod.utils.Util.onNext
import reactor.core.publisher.Flux

object Lec03FluxFromArrayOrList {
    @JvmStatic
    fun main(args: Array<String>) {

        // List<String> strings = Arrays.asList("a", "b", "c");
/*        Flux.fromIterable(strings)
                .subscribe(Util.onNext());*/
        val arr = arrayOf(2, 5, 7, 8)
        Flux.fromArray(arr)
            .subscribe(onNext())
    }
}
