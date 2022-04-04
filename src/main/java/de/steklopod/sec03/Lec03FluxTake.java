package de.steklopod.sec03;

import de.steklopod.utils.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {

    public static void main(String[] args) {

        // map
        // filter
        Flux.range(1, 10)
                .log()
                .take(3) // cancels
                .log()
                .subscribe(Util.subscriber());


    }

}
