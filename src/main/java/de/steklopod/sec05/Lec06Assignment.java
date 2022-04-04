package de.steklopod.sec05;

import de.steklopod.utils.Util;
import de.steklopod.sec05.assignment.InventoryService;
import de.steklopod.sec05.assignment.OrderService;
import de.steklopod.sec05.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // revenue and inv - observe the order stream
        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream()

                .subscribe(Util.subscriber("inventory"));

        revenueService.revenueStream()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(60);

    }


}
