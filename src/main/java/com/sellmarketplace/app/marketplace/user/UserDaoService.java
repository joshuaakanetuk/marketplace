package com.sellmarketplace.app.marketplace.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();

    static {
        users.add(new User("Adam", 1, "test@gmail.com", LocalDate.now().minusYears(30) ));
    }

    public List<User> findAll() {
        return users;
    }
}
