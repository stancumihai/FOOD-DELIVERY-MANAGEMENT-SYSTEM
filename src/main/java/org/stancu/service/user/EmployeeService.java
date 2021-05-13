package org.stancu.service.user;

import org.stancu.dao.user.EmployeeDao;
import org.stancu.model.userModel.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***
 * The business logic for the Employee Class
 */
public class EmployeeService {

    public static EmployeeDao employeeDataAccessService = new EmployeeDao();
    public static EmployeeService employeeService = null;
    private final ArrayList<String> notifications = new ArrayList<>();

    private EmployeeService(EmployeeDao employeeDataAccessService) {
        EmployeeService.employeeDataAccessService = employeeDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of EmployeeService
     */
    public static EmployeeService getInstance() {
        if (employeeService == null) {
            employeeService = new EmployeeService(employeeDataAccessService);
        }
        return employeeService;
    }

    public Employee findById(Integer id) {
        return employeeDataAccessService.findById(id);
    }

    public Employee update(Integer id, Employee model) {
        return employeeDataAccessService.update(model, id);
    }

    public void insert(Employee model) {
        employeeDataAccessService.insert(model);
    }

    public Employee delete(Integer id) {
        return employeeDataAccessService.delete(id);
    }

    public List<Employee> selectAll() {
        return employeeDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return employeeDataAccessService.getLastId();
    }

    public void notifyMe(Employee employee, int orderId) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateAndTime = LocalDateTime.now();
        String stringDateTime = myFormatObj.format(dateAndTime);
        String notification = "Employee : " + employee.getId() + "  is notified about order at : " + stringDateTime + " for order :" + orderId;
        notifications.add(notification);
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }
}
