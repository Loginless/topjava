package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public interface MealDao {

    public void addMeal(Meal meal);

    public void deleteMeal(Integer id);

    public void updateMeal(Meal meal);

    public List<Meal> getAllMeals();

    Meal getMealById(int id);
}
