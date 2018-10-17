package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));


            //Test of mealRestController functionality
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            //Get all meals for the user with id = 1
            System.out.println("Get all meals:");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("#################");
            System.out.println();

            //Create a meal for the user with id = 1
            System.out.println("Create a new meal:");
            Meal testMeal = new Meal(1, LocalDateTime.of(2015, Month.APRIL, 13, 20, 0), "Завтрак", 500);
            mealRestController.save(testMeal);
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("#################");
            System.out.println();

            //Update a meal for the user with id = 1
            System.out.println("Update a new meal:");
            testMeal.setId(12);
            mealRestController.save(testMeal);
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("#################");
            System.out.println();

            //Get a single meal for user with id = 1
            System.out.println("Get a single meal:");
            System.out.println(mealRestController.get(1));
            System.out.println("#################");
            System.out.println();

            //Get meals by date for user with id = 1
            System.out.println("Get meals by date:");
            LocalDateTime startTime = LocalDateTime.of(2015, Month.MAY, 31, 10, 0);
            LocalDateTime endTime = LocalDateTime.of(2015, Month.MAY, 31, 10, 0);
            mealRestController.getByDate(startTime.toLocalDate(), startTime.toLocalTime(), endTime.toLocalDate(), endTime.toLocalTime()).forEach(System.out::println);
            System.out.println("#################");
            System.out.println();

            //Delete a meal for user with id = 1
            System.out.println("Before deleting a meal:");
            mealRestController.getAll().forEach(System.out::println);
            System.out.println();
            System.out.println("After deleting a meal:");
            mealRestController.delete(1);
            mealRestController.getAll().forEach(System.out::println);
            System.out.println("#################");

        }
    }
}
