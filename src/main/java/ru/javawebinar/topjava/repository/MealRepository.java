package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    // false if not found or doesn't belong to user
    boolean delete(int id, int userId);

    // null if not found
    Meal get(int id, int userId);

    List<Meal> getByDate(int userId, LocalDate startDate,  LocalTime startTime, LocalDate endDate, LocalTime endTime);

    // Empty list if not found
    List<Meal> getAll(int userId);

}
