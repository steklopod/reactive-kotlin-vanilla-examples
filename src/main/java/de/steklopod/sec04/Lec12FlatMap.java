package de.steklopod.sec04;

import de.steklopod.courseutil.Util;
import de.steklopod.sec04.helper.OrderService;
import de.steklopod.sec04.helper.UserService;

import java.io.BufferedReader;

public class Lec12FlatMap {

    public static void main(String[] args) {

        BufferedReader reader;


        UserService.getUsers()
                .flatMap(user -> OrderService.getOrders(user.getUserId())) // mono / flux
               // .filter(p -> p > 10)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);


    }


}
