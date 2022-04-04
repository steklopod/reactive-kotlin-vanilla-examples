package de.steklopod.sec08;

import de.steklopod.utils.Util;
import de.steklopod.sec08.helper.AmericanAirlines;
import de.steklopod.sec08.helper.Emirates;
import de.steklopod.sec08.helper.Qatar;
import reactor.core.publisher.Flux;

public class Lec03Merge {

    public static void main(String[] args) {

        Flux<String> merge = Flux.merge(
                Qatar.getFlights(),
                Emirates.getFlights(),
                AmericanAirlines.getFlights()
        );

        merge.subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }


}
