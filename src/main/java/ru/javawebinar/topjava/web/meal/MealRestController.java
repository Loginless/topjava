package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public void save(Meal meal) {
        service.create(meal);
    }

    public List<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.authUserId()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getByDate(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getWithExceeded(service.getByDate(SecurityUtil.authUserId(), startDate, startTime, endDate, endTime), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int mealId) {
        return service.get(mealId, SecurityUtil.authUserId());
    }

    public void update(Meal meal, int id) {
        service.update(meal, id);
    }

    public void delete(int mealId) {
        service.delete(mealId, SecurityUtil.authUserId());
    }

}