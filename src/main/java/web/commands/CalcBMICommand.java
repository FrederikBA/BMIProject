package web.commands;

import business.entities.User;
import business.exceptions.UserException;
import business.services.BmiUtil;
import com.sun.org.apache.xpath.internal.operations.Number;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;

public class CalcBMICommand extends CommandUnprotectedPage {
    public CalcBMICommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        BmiUtil bmiUtil = new BmiUtil();
        Double height = 0.0;
        Double weight = 0.0;
        Double bmi = 0.0;
        String category = "";

        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));
        } catch (NumberFormatException e) {
            throw new UserException("Error in input. Must be numbers");
        }
        category = bmiUtil.getCategory(bmi);

        bmi = bmiUtil.calculateBMI(height, weight);

        DecimalFormat df = new DecimalFormat("#.##");

        request.setAttribute("bmi", df.format(bmi));
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);

        return pageToShow;
    }
}
