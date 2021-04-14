package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.BmiFacade;
import business.services.BmiUtil;
import com.sun.org.apache.xpath.internal.operations.Number;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalcBMICommand extends CommandUnprotectedPage {

    private BmiFacade bmiFacade;

    public CalcBMICommand(String pageToShow) {
        super(pageToShow);
        this.bmiFacade = new BmiFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        HttpSession session = request.getSession();
        User user;
        int userId = 1; //TODO: Should be made dynamtic in response to login.
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getId();
        } else {

        }

        BmiUtil bmiUtil = new BmiUtil();
        double height = 0.0;
        double weight = 0.0;
        double bmi = 0.0;
        String category = "";
        String gender = "";
        int sportId = 0;
        String[] hobbies;


        height = Double.parseDouble(request.getParameter("height"));
        weight = Double.parseDouble(request.getParameter("weight"));
        category = bmiUtil.getCategory(bmi);
        gender = request.getParameter("gender");
        sportId = Integer.parseInt(request.getParameter("sport"));
        hobbies = request.getParameterValues("hobby");

        List<String> hobbyListString = null;
        if (hobbies != null) {
            hobbyListString = Arrays.asList(hobbies);
        }

        List<Integer> hobbyListIntegers = new ArrayList<>();

        for (String s : hobbyListString) {
            hobbyListIntegers.add(Integer.parseInt(s));
        }


        bmi = bmiUtil.calculateBMI(height, weight);
        category = bmiUtil.getCategory(bmi);

        DecimalFormat df = new DecimalFormat("#.##");

        request.setAttribute("bmi", df.format(bmi));
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);
        request.setAttribute("gender", gender);
        request.setAttribute("sport_id", sportId);
        request.setAttribute("hobbies", hobbyListIntegers);

        bmiFacade.insertBmiEntry(bmi, height, weight, category, gender, sportId, userId, hobbyListIntegers);

        return pageToShow;
    }
}
