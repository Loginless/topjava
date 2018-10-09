package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int caloriesPerDay = 1000;
        List<MealWithExceed> resultList = new ArrayList<>();
        for (Meal meal : MealDaoImpl.meals) {
            boolean calCheck = meal.getCalories() > caloriesPerDay;
            resultList.add(MealsUtil.createWithExceed(meal, calCheck));
        }

        request.setAttribute("mealList", resultList);
        request.getRequestDispatcher("meals.jsp").forward(request, response);

    }
}
