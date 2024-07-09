package src;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    private List<Employee> employees;

    // Create
    public EmployeeCRUD() {
        this.employees = new ArrayList<>();
    }
    // Add
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Read all
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // Read by ID
    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    // Update
    public boolean updateEmployee(int id, String newName, String newDepartment) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(newName);
                employee.setDepartment(newDepartment);
                return true; // Update successful
            }
        }
        return false; // Employee not found
    }

    // Delete
    public boolean deleteEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employees.remove(employeeToRemove);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        // Create
        employeeCRUD.addEmployee(new Employee(1, "Anna", "IT"));
        employeeCRUD.addEmployee(new Employee(2, "Cindy", "IT"));

        // Read
        List<Employee> allEmployees = employeeCRUD.getAllEmployees();
        System.out.println("All Employees:");
        for (Employee employee : allEmployees) {
            System.out.println(employee);
        }
        int idIndex = 1;
        Employee foundEmployee = employeeCRUD.getEmployeeById(idIndex);
        if (foundEmployee != null) {
            System.out.println("\nEmployee with ID " + idIndex + ": " + foundEmployee);
        } else {
            System.out.println("\nEmployee with ID " + idIndex + " not found.");
        }

        // Update
        int idToUpdate = 2;
        boolean updateResult = employeeCRUD.updateEmployee(idToUpdate, "Cindy", "HR");
        if (updateResult) {
            System.out.println("\nEmployee with ID " + idToUpdate + " updated successfully.");
        } else {
            System.out.println("\nEmployee with ID " + idToUpdate + " not found for update.");
        }

        // Delete
        int idToDelete = 1;
        boolean deleteResult = employeeCRUD.deleteEmployee(idToDelete);
        if (deleteResult) {
            System.out.println("\nEmployee with ID " + idToDelete + " deleted successfully.");
        } else {
            System.out.println("\nEmployee with ID " + idToDelete + " not found for deletion.");
        }

        // Display
        System.out.println("\nUpdated Employees List:");
        List<Employee> updatedEmployees = employeeCRUD.getAllEmployees();
        for (Employee employee : updatedEmployees) {
            System.out.println(employee);
        }
    }
}
