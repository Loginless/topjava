package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal mealTest = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(mealTest, USER_TEST_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void getWrongUserID() throws Exception {
        Meal mealTest = service.get(USER_MEAL_ID, ADMIN_ID);
        assertMatch(mealTest, USER_TEST_MEAL);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(USER_MEAL_ID, USER_ID);
        service.get(USER_MEAL_ID, USER_ID);
    }

    @Test
    public void getAllUserMeals() throws Exception {
        List<Meal> allUserMeal = service.getAll(USER_ID);
        assertMatch(allUserMeal, USER_TEST_MEAL, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getAllAdminMeals() throws Exception {
        List<Meal> allUserMeal = service.getAll(ADMIN_ID);
        assertMatch(allUserMeal, ADMIN_TEST_MEAL, MEAL7);
    }

    @Test
    public void create() throws Exception {
        Meal meal = new Meal(LocalDateTime.of(2018, 10, 24, 10, 0, 0),
                "Food", 2300);
        Meal testMeal = service.create(meal, USER_ID);
        assertMatch(testMeal, meal);
    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(USER_TEST_MEAL);
        meal.setCalories(330);
        meal.setDescription("Update");
        Meal testMeal = service.update(meal, USER_ID);
        assertMatch(testMeal, meal);
    }


    @Test
    public void getBetweenDateTimes() {
        final LocalDateTime startDate = LocalDateTime.of(2018, 5, 23, 9, 00, 00);
        final LocalDateTime endDate = LocalDateTime.of(2018, 5, 24, 15, 00, 00);
        List<Meal> allMeals = service.getBetweenDateTimes(startDate, endDate, USER_ID);
        assertMatch(allMeals, MEAL3, MEAL2, MEAL1);
    }

}