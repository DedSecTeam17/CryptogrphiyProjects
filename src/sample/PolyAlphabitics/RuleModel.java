package sample.PolyAlphabitics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RuleModel {
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/rules";
    private static final String USERNAME = "root";
    private static final String Password = "";
    private static RuleModel ourInstance = new RuleModel();

    public static RuleModel getInstance() {
        return ourInstance;
    }

    private RuleModel() {
    }
    public Connection JDBC_Connection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, Password);
    }
    public void addRuleToDb(String type, int amount, boolean direction) throws SQLException {
        Connection connection = JDBC_Connection();
        String sql = "INSERT INTO rule(type,direction,amount) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, type);
        preparedStatement.setBoolean(2, direction);
        preparedStatement.setInt(3, amount);
        preparedStatement.executeUpdate();

    }
    public List<Rule> getAllRules() throws SQLException {
        Connection connection = JDBC_Connection();
        String sql = "SELECT  * FROM  rule ORDER  by id ASC ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery(sql);
        List<Rule> rules = new ArrayList<>(20);
        while (set.next()) {
            String type = set.getString("type");
            int amount = set.getInt("amount");
            boolean direction = set.getBoolean("direction");
            int id = set.getInt("id");
            rules.add(new Rule(type,amount,direction,id));
        }
        return  rules;
    }
    public  void  deleteRule(int id) throws SQLException {
        Connection connection = JDBC_Connection();
        String sql = String.format("DELETE   FROM rule WHERE  id=%d", id);
        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);
//        Statement statement = connection.prepareStatement(sql);
//      statement.execute(sql);
    }








}
