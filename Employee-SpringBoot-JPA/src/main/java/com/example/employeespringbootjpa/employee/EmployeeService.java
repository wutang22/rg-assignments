package com.example.employeespringbootjpa.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public Optional<Employee> getEmployeeById(int id) {
        return repository.findById(id);
    }

    public void postEmployee(Employee emp){
        repository.save(emp);
    }

    public void deleteEmployee(int id){
        repository.deleteById(id);
    }

    @Transactional
    public void updateEmployee(Employee emp) {
        Employee databaseEmp = repository.findById(emp.getId())
                .orElseThrow(() -> new RuntimeException("Cannot find employee with id: " + emp.getId()));

        databaseEmp.setName(emp.getName());
        databaseEmp.setDepartment(emp.getDepartment());

        repository.save(databaseEmp);
    }
}
