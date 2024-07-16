package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeCRUD {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        // Create
        Employee newEmployee = new Employee(1, "Anna", "IT");
        createEmployee(jdbcTemplate, newEmployee);
        Employee newEmployee2 = new Employee(2, "Cindy", "IT");
        createEmployee(jdbcTemplate, newEmployee2);

        // Read
        List<Employee> employees = getAllEmployees(jdbcTemplate);
        System.out.println("All employees: ");
        for (Employee emp : employees) {
            System.out.println(emp.getId() + ": " + emp.getName() + " - " + emp.getDepartment());
        }

        // Update
        Employee updatedEmployee = new Employee(2, "Cindy", "HR");
        updateEmployee(jdbcTemplate, updatedEmployee);

        // Delete
        deleteEmployee(jdbcTemplate, 1);

        //
        List<Employee> currentEmployees = getAllEmployees(jdbcTemplate);
        System.out.println("Current employees: ");
        for (Employee emp : currentEmployees) {
            System.out.println(emp.getId() + ": " + emp.getName() + " - " + emp.getDepartment());
        }

        context.close();
    }

    private static void createEmployee(JdbcTemplate jdbcTemplate, Employee employee) {
        String sql = "INSERT INTO employee (id, name, department) VALUES (?,?, ?)";
        int createResult = jdbcTemplate.update(sql,employee.getId(), employee.getName(), employee.getDepartment());
        System.out.println("Employee created successfully: "+ createResult);
    }

    private static List<Employee> getAllEmployees(JdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    private static void updateEmployee(JdbcTemplate jdbcTemplate, Employee employee) {
        String sql = "UPDATE employee SET name = ?, department = ? WHERE id = ?";
        int updateResult = jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getId());
        System.out.println("Employee updated successfully: " + updateResult);

    }

    private static void deleteEmployee(JdbcTemplate jdbcTemplate, int id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        int deleteResult = jdbcTemplate.update(sql, id);
        System.out.println("Employee deleted successfully: " + deleteResult);
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department")
            );
        }
    }
}