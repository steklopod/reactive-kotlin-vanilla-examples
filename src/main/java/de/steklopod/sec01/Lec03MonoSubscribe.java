package de.steklopod.sec01;

import de.steklopod.courseutil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        // publisher
        Mono<Integer> mono = Mono.just("ball")
                                .map(String::length)
                                .map(l -> l / 1);

        // 1
        // mono.subscribe();

        // 2
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );


    }

}
