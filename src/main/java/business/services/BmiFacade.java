package business.services;

import business.entities.BmiEntry;
import business.exceptions.UserException;
import business.persistence.BmiMapper;
import business.persistence.Database;

import java.util.List;

public class BmiFacade {

    private BmiMapper bmiMapper;

    public BmiFacade(Database database) {
        this.bmiMapper = new BmiMapper(database);
    }

    public void insertBmiEntry(double bmi, double height, double weight, String category, String gender, int sportId, int userId, List<Integer> hobbyListInteger) throws UserException {
        bmiMapper.insertBmiEntry(bmi, height, weight, category, gender, sportId, userId, hobbyListInteger);
    }

    public List<BmiEntry> getAllBmiDataEntries() throws UserException {

        return bmiMapper.getAllBmiDataEntries();
    }

}
