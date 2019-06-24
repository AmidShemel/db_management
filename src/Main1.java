import java.sql.*;

/**
 * Програма виконує авторизацію за допомогою полів 'login' та 'password' існуючих в базі 'dbautorization'.
 * Якщо пара, логін-пароль, не співпадає - авторизація не відбувається
 */

public class Main1 {

    public static String login = "HizZ";
    public static String pass = "root";
    public static String db_name = "dbautorization";
    public static String dbURL = "jdbc:mysql://localhost:3306/"+db_name+"?useSSL=false&serverTimezone=UTC";
    private static Connection connection;

    public static void main(String[] args) {
        System.out.println("RUN Main1");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(dbURL, login, pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        logIn("Director", "12345");

    }

    public static void logIn(String login, String password) {

        try {
            PreparedStatement preparedStatement;
            preparedStatement= connection.prepareStatement("select * from users where login=? and password=?");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                String log = res.getString("login");
                String pass = res.getString("password");

                System.out.println(log + "\t" + pass);

                if (!log.isEmpty() & !pass.isEmpty()){
                    System.out.println("authorization allow!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

//    Connection connection;

//    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//
//    connection = DriverManager.getConnection(dbURL, login, pass);
//
//    Statement statement = connection.createStatement();

//    System.out.println(connection.isClosed());

//    statement.execute("insert into users (name, age, email) values ('Mac', 18, 'mac@gmail.com')");

//    statement.executeUpdate("update users set name='Jack', age=21 where id=10");
//    statement.executeUpdate("update users set email='jack@gmail.com' where name='Jack'");

//    ResultSet res = statement.executeQuery("select * from users");
//
//    statement.addBatch("INSERT into users (name, age, email) VALUES ('Bob', 45, 'bobby@gmail.com')");
//    statement.executeBatch();
//    statement.clearBatch();

//
//    statement.close();
//
//    connection.close();
