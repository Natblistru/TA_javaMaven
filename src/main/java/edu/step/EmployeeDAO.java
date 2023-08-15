package edu.step;

import java.sql.*;

public class EmployeeDAO {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/employeebd";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }
    public void createEmployee(String nume, String departament, double salariu) {
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO employees(nume, departament, salariu) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setString(2, departament);
            statement.setDouble(3, salariu);
            int rows = statement.executeUpdate();
            System.out.println("S-au modificat " + rows + " randuri");
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nu am putut face insertul: " + ex.getMessage());
        }
    }
    public void updateEmployee(Integer id, String nume, String departament, double salariu) {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE employees SET nume=?, departament=?, salariu=? where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nume);
            statement.setString(2, departament);
            statement.setDouble(3, salariu);
            statement.setInt(4, id);
            int rows = statement.executeUpdate();
            System.out.println("S-au modificat " + rows + " randuri");
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nu am putut face insertul: " + ex.getMessage());
        }
    }
    public void deleteEmployee(Integer id) {
        try {
            Connection connection = getConnection();
            String sql = "DELETE from employees  where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            System.out.println("S-au șters " + rows + " randuri");
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nu am putut face delete: " + ex.getMessage());
        }
    }

    public void selectAllEmployees () {
        try {
            Connection connection = getConnection();
            String sql = "SELECT id, nume, departament, salariu from employees";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String departament = resultSet.getString("departament");
                Double salariu = resultSet.getDouble("salariu");
                System.out.println(id+" "+ nume + " " + departament + " " + salariu);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nu am putut face selectul: " + ex.getMessage());
        }
    }
    public void getEmployeeById (int employeeId) {
        try {
            Connection connection = getConnection();
            String sql = "SELECT id, nume, departament, salariu FROM employees WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nume = resultSet.getString("nume");
                String departament = resultSet.getString("departament");
                Double salariu = resultSet.getDouble("salariu");
                System.out.println(id+" "+ nume + " " + departament + " " + salariu);
            }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Nu s-a putut obține angajatul: " + ex.getMessage());
        }
    }
}
