package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ppbootcamp";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "rgrgrg";

    // Connection
    private Connection connection;
    public EmployeeJDBC() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // SQL
    private PreparedStatement preparedStatement;
    private static final String INSERT_SQL = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT id, name, department FROM employees";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, department FROM employees WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM employees WHERE id = ?";


    // Create
    public void addEmployee(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_SQL);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getDepartment());
        preparedStatement.executeUpdate();
    }

    // Read all
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            employees.add(new Employee(id, name, department));
        }
        return employees;
    }

    // Read by ID
    public Employee getEmployeeById(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String department = resultSet.getString("department");
            return new Employee(id, name, department);
        }
        return null;
    }

    // Update
    public boolean updateEmployee(Employee employee) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE_SQL);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getDepartment());
        preparedStatement.setInt(3, employee.getId());
        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;
    }

    // Delete
    public boolean deleteEmployee(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_SQL);
        preparedStatement.setInt(1, id);
        int rowsDeleted = preparedStatement.executeUpdate();
        return rowsDeleted > 0;
    }

    // Close
    public void close() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        try {
            EmployeeJDBC employeeJDBC = new EmployeeJDBC();

            // Create
            Employee newEmployee = new Employee(1, "Anna", "IT");
            employeeJDBC.addEmployee(newEmployee);
            Employee newEmployee2 = new Employee(2, "Cindy", "IT");
            employeeJDBC.addEmployee(newEmployee2);


            // Read all
            List<Employee> allEmployees = employeeJDBC.getAllEmployees();
            System.out.println("All Employees:");
            for (Employee employee : allEmployees) {
                System.out.println(employee);
            }

            // Read by ID
            int idToFind = 1;
            Employee foundEmployee = employeeJDBC.getEmployeeById(idToFind);
            if (foundEmployee != null) {
                System.out.println("\nEmployee with ID " + idToFind + ": " + foundEmployee);
            } else {
                System.out.println("\nEmployee with ID " + idToFind + " not found.");
            }

            // Update
            Employee employeeToUpdate = new Employee(2, "Cindy", "HR");
            boolean updateResult = employeeJDBC.updateEmployee(employeeToUpdate);
            if (updateResult) {
                System.out.println("\nEmployee updated successfully.");
            } else {
                System.out.println("\nEmployee not found for update.");
            }

            // Delete
            int idToDelete = 1;
            boolean deleteResult = employeeJDBC.deleteEmployee(idToDelete);
            if (deleteResult) {
                System.out.println("\nEmployee with ID " + idToDelete + " deleted successfully.");
            } else {
                System.out.println("\nEmployee with ID " + idToDelete + " not found for deletion.");
            }

            // Close JDBC resources
            employeeJDBC.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
