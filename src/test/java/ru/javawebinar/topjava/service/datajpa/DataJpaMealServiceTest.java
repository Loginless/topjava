package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.service.AbstractMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ActiveProfiles(DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void getMealWithUserCompMeal() {
        assertMatch(service.getMealWithUser(MEAL1_ID, USER_ID), MEAL1);
    }

    @Test
    public void getMealWithUserCompUser() {
        UserTestData.assertMatch(service.getMealWithUser(MEAL1_ID, USER_ID).getUser(), USER);
    }
}
