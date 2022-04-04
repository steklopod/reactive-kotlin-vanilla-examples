package de.steklopod.sec02;

import de.steklopod.courseutil.Util;
import de.steklopod.sec02.helper.NameGenerator;

public class Lec07FluxVsList {

    public static void main(String[] args) {

       // List<String> names = NameGenerator.getNames(5);
        //System.out.println(names);

        NameGenerator.getNames(5)
                .subscribe(Util.onNext());



    }

}
