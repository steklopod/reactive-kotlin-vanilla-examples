package de.steklopod.sec04;

import de.steklopod.utils.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {

        getOrderNumbers()
                .filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getOrderNumbers(){
        return Flux.range(1, 12);
    }


}
