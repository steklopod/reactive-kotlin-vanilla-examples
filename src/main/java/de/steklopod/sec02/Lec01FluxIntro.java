package de.steklopod.sec02;

import de.steklopod.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {

    public static void main(String[] args) {

        Flux<Object> flux = Flux.just(1,2, 3, "a", Util.faker().name().fullName());

        flux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());


    }

}
