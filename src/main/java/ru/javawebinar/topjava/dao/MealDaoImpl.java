package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoImpl implements MealDao {
    private static final Logger log = getLogger(MealDaoImpl.class);

    public static Map<Integer, Meal> meals = new ConcurrentHashMap();
    private static AtomicInteger mealId = new AtomicInteger(0);

    static {
        meals.put(mealId.incrementAndGet(), new Meal(mealId.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(mealId.incrementAndGet(), new Meal(mealId.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(mealId.incrementAndGet(), new Meal(mealId.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(mealId.incrementAndGet(), new Meal(mealId.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(mealId.incrementAndGet(), new Meal(mealId.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        log.info("Map created");
    }


    @Override
    public void addMeal(Meal meal) {
        meals.putIfAbsent(mealId.incrementAndGet(), meal);
    }

    @Override
    public void deleteMeal(Integer id) {
        meals.remove(id);
        log.info("Meal Deleted" + id);
    }

    @Override
    public void updateMeal(Meal meal) {
        meals.put(meal.getId(), meal);

    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getMealById(int id) {
        return meals.get(id);
    }
}
