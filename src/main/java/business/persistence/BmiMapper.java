package business.persistence;

import business.entities.BmiEntry;
import business.entities.Sport;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BmiMapper {

    private Database database;

    public BmiMapper(Database database) {
        this.database = database;
    }

    public List<BmiEntry> getAllBmiDataEntries() throws UserException {
        List<BmiEntry> bmiEntryList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bmi_entry";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("bmi_entry_id");
                    int height = rs.getInt("height");
                    int weight = rs.getInt("weight");
                    String category = rs.getString("category");
                    double bmi = rs.getDouble("bmi");
                    String gender = rs.getString("gender");


                    BmiEntry tmpBmiEntry = new BmiEntry(id, height, weight, category, bmi, gender);
                    bmiEntryList.add(tmpBmiEntry);
                }
                return bmiEntryList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public List<BmiEntry> getBmiDataEntriesByUserId(int userId) throws UserException {
        List<BmiEntry> bmiEntryList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM bmi_entry WHERE user_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("bmi_entry_id");
                    int height = rs.getInt("height");
                    int weight = rs.getInt("weight");
                    String category = rs.getString("category");
                    double bmi = rs.getDouble("bmi");
                    String gender = rs.getString("gender");

                    BmiEntry tmpBmiEntry = new BmiEntry(id, height, weight, category, bmi, gender);
                    bmiEntryList.add(tmpBmiEntry);
                }
                return bmiEntryList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
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

    public List<Sport> getAllSports() throws UserException {
        List<Sport> sportsList = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM sport";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("sport_id");
                    String name = rs.getString("name");


                    Sport tmpSport = new Sport(id, name);
                    sportsList.add(tmpSport);
                }
                return sportsList;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public Sport getSportById(int sportId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM sport WHERE sport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, sportId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("sport_id");
                    String name = rs.getString("name");
                    return new Sport(id, name);
                }
                throw new UserException("Sport doesn't exist for sport_id = " + sportId);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    public int deleteSport(int sportId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM SPORT WHERE sport_id = ? AND sport_id NOT IN (SELECT sport_id FROM bmi_entry)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, sportId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    public int updateSport(int sportId, String name) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "UPDATE sport SET name = ? WHERE sport_id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, sportId);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
