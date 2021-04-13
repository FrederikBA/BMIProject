package business.services;

public class BmiUtil {

    public double calculateBMI(Double height, Double weight) {

        return weight / ((height / 100) * (height / 100));
    }

    public String getCategory(Double bmi) {
        String category = "";
        if (bmi >= 30) {
            category = "Obese";
        } else if (bmi >= 25) {
            category = "Over weight";
        } else if (bmi >= 18.5) {
            category = "Normal weight";
        } else {
            category = "Under weight";
        }
        return category;
    }

}
