package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN),
            new User(null, "userName1", "email1@mail.ru", "password1", Role.ROLE_USER),
            new User(null, "userName2", "email2@mail.ru", "password2", Role.ROLE_USER),
            new User(null, "userName3", "email3@mail.ru", "password3", Role.ROLE_USER),
            new User(null, "userName4", "email4@mail.ru", "password4", Role.ROLE_USER)
    );
}
