package org.stancu.dao.user;

import org.stancu.dao.DAO;
import org.stancu.model.userModel.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements DAO<Employee> {

    private ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public Employee findById(Integer id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> selectAll() {
        return employees;
    }

    @Override
    public Employee delete(Integer id) {
        Employee employee = findById(id);
        if (employee != null) {
            employees.removeIf(client1 -> client1.equals(employee));
            return employee;
        } else return null;
    }

    @Override
    public Employee update(Employee model, Integer id) {
        Employee employee = findById(id);
        if (employee != null) {
            int cnt = 0;
            for (Employee employee1 : employees) {
                cnt++;
                if (employee1.equals(employee)) {
                    employees.remove(employee1);
                    employees.add(cnt, model);
                    return model;
                }
            }
        }
        return null;
    }

    @Override
    public Employee insert(Employee model) {
        if (employees.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId()+1);
        }
        employees.add(model);
        return model;
    }

    @Override
    public Integer getLastId() {
        return employees.get(employees.size() - 1).getId();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
