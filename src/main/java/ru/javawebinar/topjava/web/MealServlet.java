package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao mealDao = new MealDaoImpl();
    private static String INSERT_OR_EDIT = "/editMeals.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        System.out.println(action);
        List<MealWithExceed> resultList;
        int caloriesPerDay = 500;

        if (action == null) {

            resultList = MealsUtil.getFilteredWithExceeded(
                    mealDao.getAllMeals(),
                    LocalTime.MIN,
                    LocalTime.MAX,
                    caloriesPerDay);
            resultList.sort((a, b) -> a.getDateTime().isBefore(b.getDateTime()) ? -1 : 1);
            forward = LIST_MEALS;
            request.setAttribute("mealList", resultList);

        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.deleteMeal(mealId);
            resultList = MealsUtil.getFilteredWithExceeded(
                    mealDao.getAllMeals(),
                    LocalTime.MIN,
                    LocalTime.MAX,
                    caloriesPerDay);
            resultList.sort((a, b) -> a.getDateTime().isBefore(b.getDateTime()) ? -1 : 1);
            forward = LIST_MEALS;
            request.setAttribute("mealList", resultList);
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDao.getMealById(mealId);
            request.setAttribute("mealedit", meal);
        }

        request.getRequestDispatcher(forward).forward(request, response);
    }
}

