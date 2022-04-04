package de.steklopod.sec04.helper;

import de.steklopod.utils.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Util.faker().name().fullName();
    }

}
