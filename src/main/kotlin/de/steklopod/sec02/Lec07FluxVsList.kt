package de.steklopod.sec02

import de.steklopod.sec02.helper.NameGenerator
import de.steklopod.utils.Util.onNext

object Lec07FluxVsList {
    @JvmStatic
    fun main(args: Array<String>) {

        // List<String> names = NameGenerator.getNames(5);
        //System.out.println(names);
        NameGenerator.getNames(5)
            .subscribe(onNext())
    }
}
