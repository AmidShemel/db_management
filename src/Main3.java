import java.sql.*;

/**
 * Програма створює серію таблиць в базі даних вказаній в аргкменті 'destDB', для кожного користувача з таблиці
 * вказаній в аргументі 'sourceTab' методу startCreatingTables()
 */

public class Main3 {

    private static String login = "HizZ";
    private static String pass = "root";
    private static String db_name = "mydbtest";
    private static String dbURL = "jdbc:mysql://localhost:3306/" + db_name + "?useSSL=false&serverTimezone=UTC";

    public static void main(String[] args) {

//        startCreatingTables("db_education.students", "db_balls");
//        startCreatingTables("dbautorization.users", "mydbtest");
//        startCreatingTables("mydbtest.users", "mydbtest");
//        startCreatingTables("mydbtest.users", "mydbtest");

    }

    private static void startCreatingTables(String sourceTab, String destDB) {
        System.out.println("RUN Main3!\n" + sourceTab);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbURL, login, pass);
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            System.out.println(!connection.isClosed());

            ResultSet res = statement2.executeQuery("SELECT * FROM " + sourceTab);

            while (res.next()) {
                String id = res.getString(1);
                String last_name = res.getString(2);
                String first_name = res.getString(3);
                String sur_name = res.getString(4);

                System.out.println(id);
            statement1.execute("CREATE TABLE IF NOT EXISTS "+destDB+".ballsofuser_" + id + " (\n" +
                    "  date VARCHAR(45) NOT NULL,\n" +
                    "  ball INT NOT NULL,\n" +
                    "  ball_tipe_id INT NOT NULL,\n" +
                    "  discipline_id INT NOT NULL,\n" +
                    "  techer_id INT NOT NULL,\n" +
                    "  coment VARCHAR(45) NULL)\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8\n" +
                    "COMMENT = 'Оцінки користувача: "+last_name+" "+first_name+" "+sur_name+ " id(" +id+")';");
            }

            connection.close();
            statement1.close();
            statement2.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
