import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Програма створює серію таблиць з новим ім'ям ітеруючи число в назві таблиці. Створення починається з першого
 * переданого параметра вказоного в методі startCreatingTables() і закінчую цифрою вказанию в другому параметрі
 */

public class Main2 {

    private static String login = "HizZ";
    private static String pass = "root";
    private static String db_name = "mydbtest";
    private static String dbURL = "jdbc:mysql://localhost:3306/"+db_name+"?useSSL=false&serverTimezone=UTC";

    public static void main(String[] args) {

        startCreatingTables(1, 5);


    }

    private static void startCreatingTables(int start, int finish) {
        System.out.println("RUN Main2! From: " + start + " To: " + finish);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(dbURL, login, pass);

            Statement statement = connection.createStatement();

            System.out.println(!connection.isClosed());

            for (int i = start; i <= finish; i++) {

                statement.execute("CREATE TABLE new_tab_" + i + " (\n" +
                        "  date VARCHAR(45) NOT NULL,\n" +
                        "  ball INT NOT NULL,\n" +
                        "  ball_tipe_id INT NOT NULL,\n" +
                        "  discipline_id INT NOT NULL,\n" +
                        "  techer_id INT NOT NULL,\n" +
                        "  coment VARCHAR(45) NULL)\n" +
                        "ENGINE = InnoDB\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "COMMENT = 'balls of user: ';");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
