package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);

    private Map<Integer, Meal> repositoryMeals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repositoryMeals.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repositoryMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = repositoryMeals.get(id);
        if (meal != null && meal.getUserId() == userId) {
            log.info("meal get {}", id);
            return meal;
        } else return null;
    }

    @Override
    public List<Meal> getByDate(int userId, LocalDateTime startDate, LocalDateTime endDate) {
        return repositoryMeals.values()
                .stream()
                .filter(meal -> (meal.getUserId() == userId))
                .filter(meal -> (startDate == null) || DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .filter(meal -> (endDate == null) || DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getByDate(userId, null, null);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repositoryMeals.containsKey(id) && (repositoryMeals.get(id).getUserId() == userId)) {
            repositoryMeals.remove(id);
            log.info("meal deleted {}", id);
            return true;
        }
        return false;
    }
}

