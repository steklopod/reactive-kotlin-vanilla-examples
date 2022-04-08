package de.steklopod.sec02.helper

import de.steklopod.utils.Util.faker
import de.steklopod.utils.Util.sleepSeconds
import reactor.core.publisher.Flux

object NameGenerator {
    /*    public static List<String> getNames(int count){
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }*/
    fun getNames(count: Int): Flux<String> {
        return Flux.range(0, count)
            .map { i: Int? -> name }
    }

    private val name: String
        private get() {
            sleepSeconds(1)
            return faker().name().fullName()
        }
}
