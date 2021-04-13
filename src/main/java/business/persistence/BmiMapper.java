package business.persistence;

import business.exceptions.UserException;

import java.sql.*;
import java.util.List;

public class BmiMapper {

    private Database database;

    public BmiMapper(Database database) {
        this.database = database;
    }

    public void insertBmiEntry(double bmi, double height, double weight, String category, String gender, int sportId, int userId, List<Integer> hobbyListInteger) throws UserException {
        //TODO: Insert data into database.
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO bmi_entry (height,weight,category,bmi,gender,sport_id,user_id) VALUES (?, ?, ?,?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, height);
                ps.setDouble(2, weight);
                ps.setString(3, category);
                ps.setDouble(4, bmi);
                ps.setString(5, gender);
                ps.setInt(6, sportId);
                ps.setInt(7, userId);

                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                int bmiEntryId = ids.getInt(1);
                //TODO: Insert hobbies in link_hobby_bmi_entry table
                for (Integer hobbyId : hobbyListInteger) {
                    insertIntoLinkHobbyBmiEntry(bmiEntryId, hobbyId);
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public void insertIntoLinkHobbyBmiEntry(int bmiEntryId, int hobbyId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO link_bmi_hobby (hobby_id, bmi_entry_id) VALUES (?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setDouble(1, hobbyId);
                ps.setDouble(2, bmiEntryId);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }
}