package de.steklopod.sec04;

import de.steklopod.utils.Util;
import de.steklopod.sec04.helper.Person;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

            getPerson()
                    .transform(applyFilterMap())
                    .subscribe(Util.subscriber());
    }

    public static Flux<Person> getPerson(){
        return Flux.range(1, 10)
                    .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return flux -> flux
                            .filter(p -> p.getAge() > 10)
                            .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                            .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
    }



}
