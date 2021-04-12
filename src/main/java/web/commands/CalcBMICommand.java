package web.commands;

import business.entities.User;
import business.exceptions.UserException;
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

        bmi = weight / ((height / 100) * (height / 100));

        DecimalFormat df = new DecimalFormat("#.##");


        if (bmi >= 30) {
            category = "Obese";
        } else if (bmi >= 25) {
            category = "Over weight";
        } else if (bmi >= 18.5) {
            category = "Normal weight";
        } else {
            category = "Under weight";
        }

        request.setAttribute("bmi", df.format(bmi));
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);

        return pageToShow;
    }
}
