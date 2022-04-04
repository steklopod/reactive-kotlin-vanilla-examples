package de.steklopod.sec03;

import de.steklopod.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            String country;
            do{
                country = Util.faker().country().name();
                fluxSink.next(country);
            }while (!country.toLowerCase().equals("canada"));
            fluxSink.complete();
        })
        .subscribe(Util.subscriber());



    }

}
