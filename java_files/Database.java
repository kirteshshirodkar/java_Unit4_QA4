import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {

    private static final String URL = "jdbc:sqlite:students.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT," +
                     "rollno TEXT," +
                     "course TEXT)";

        try (Connection con = connect();
             Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(String name, String rollno, String course) {
        String sql = "INSERT INTO students(name, rollno, course) VALUES(?,?,?)";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, rollno);
            ps.setString(3, course);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Student> getStudents() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM students";

        try (Connection con = connect();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("rollno"),
                        rs.getString("course")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
