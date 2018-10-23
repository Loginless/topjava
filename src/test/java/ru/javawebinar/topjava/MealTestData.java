package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


public class MealTestData {

    public static final int USER_MEAL_ID = 100007;
    public static final int ADMIN_MEAL_ID = 100009;

    public static final Meal MEAL1 = new Meal(100002, LocalDateTime.of(2018, 5, 23, 10, 00), "Potato", 1300);
    public static final Meal MEAL2 = new Meal(100003, LocalDateTime.of(2018, 5, 23, 13, 00), "Fish and Mushrooms", 1200);
    public static final Meal MEAL3 = new Meal(100004, LocalDateTime.of(2018, 5, 23, 20, 00), "Soup", 400);
    public static final Meal MEAL4 = new Meal(100005, LocalDateTime.of(2018, 6, 5, 10, 00), "Beer and Chips", 5000);
    public static final Meal MEAL5 = new Meal(100006, LocalDateTime.of(2018, 6, 5, 15, 00), "Sweets", 1000);
    public static final Meal USER_TEST_MEAL = new Meal(USER_MEAL_ID, LocalDateTime.of(2018, 6, 5, 21, 00), "Biscuits", 250);
    public static final Meal MEAL7 = new Meal(100008, LocalDateTime.of(2018, 10, 23, 8, 00), "Breakfast", 2500);
    public static final Meal ADMIN_TEST_MEAL = new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2018, 10, 23, 20, 00), "Dinner", 3500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }
}

