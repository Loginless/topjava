package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.meal.AbstractMealController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController extends AbstractMealController {

    @Autowired
    private MealService service;

    @GetMapping()
    public String getAllMeals(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping(value = "/create")
    public String createMeal(Model model) {
        final Meal meal = super.create(new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "test", 1000));
        model.addAttribute("meal", meal);
        return "redirect:mealForm";
    }

    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("meal", super.get(id));
        return "redirect:mealForm";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable int id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @PostMapping("/filter")
    public String getFilteredMeals(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                   @RequestParam(value = "startTime", required = false) LocalTime startTime,
                                   @RequestParam(value = "endDate", required = false) LocalDate endDate,
                                   @RequestParam(value = "endTime", required = false) LocalTime endTime, Model model) {
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

}
