package business.entities;

public class BmiEntry {
    private int id;
    private double height;
    private double weight;
    private String category;
    private double bmi;
    private String gender;
    private Sport sport;
    private User user;

    public BmiEntry(int id, double height, double weight, String category, double bmi, String gender) {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.category = category;
        this.bmi = bmi;
        this.gender = gender;
        this.user = null;
        this.sport = null;
    }

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public double getBmi() {
        return bmi;
    }

    public String getGender() {
        return gender;
    }

    public Sport getSport() {
        return sport;
    }

    public User getUser() {
        return user;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
