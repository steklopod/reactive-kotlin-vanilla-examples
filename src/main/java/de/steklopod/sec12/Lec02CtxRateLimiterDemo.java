package de.steklopod.sec12;

import de.steklopod.courseutil.Util;
import de.steklopod.sec12.helper.BookService;
import de.steklopod.sec12.helper.UserService;
import reactor.util.context.Context;

public class Lec02CtxRateLimiterDemo {

    public static void main(String[] args) {

        BookService.getBook()
                .repeat(3)
                .contextWrite(UserService.userCategoryContext())
                .contextWrite(Context.of("user", "mike"))
                .subscribe(Util.subscriber());





    }


}
