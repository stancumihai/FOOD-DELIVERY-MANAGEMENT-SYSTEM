package org.stancu.service.user;

import org.stancu.dao.DAO;
import org.stancu.dao.user.AdministratorDao;
import org.stancu.model.MenuItem;
import org.stancu.model.Order;
import org.stancu.model.userModel.Administrator;
import org.stancu.model.userModel.Client;
import org.stancu.model.userModel.User;
import org.stancu.service.OrderService;
import org.stancu.service.ProductService;
import org.stancu.util.SorterByName;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/***
 * The business logic for the Admin Class
 */
public class AdministratorService {


    public static DAO<Administrator> administratorDataAccessService = new AdministratorDao();
    public static AdministratorService adminService = null;
    public static OrderService orderService = OrderService.getInstance();
    public static UserService userService = UserService.getInstance();
    public static ClientService clientService = ClientService.getInstance();
    public static ProductService productService = ProductService.getInstance();

    private AdministratorService(DAO<Administrator> administratorDataAccessService) {
        AdministratorService.administratorDataAccessService = administratorDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of AdminService
     */
    public static AdministratorService getInstance() {
        if (adminService == null) {
            adminService = new AdministratorService(administratorDataAccessService);
        }
        return adminService;
    }

    public Administrator findById(Integer id) {
        return administratorDataAccessService.findById(id);
    }

    public Administrator update(Integer id, Administrator model) {
        return administratorDataAccessService.update(model, id);
    }

    public void insert(Administrator model) {
        administratorDataAccessService.insert(model);
    }

    public Administrator delete(Integer id) {
        return administratorDataAccessService.delete(id);
    }

    public List<Administrator> selectAll() {
        return administratorDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return administratorDataAccessService.getLastId();
    }

    public List<Order> reportOfTimeInterval(LocalTime start, LocalTime end) {
        Map<Integer, Order> orders = orderService.selectAll();
        return orders.values().stream()
                .filter(order -> order.getOrderTime().isAfter(start) && order.getOrderTime().isBefore(end))
                .collect(Collectors.toList());
    }

    public List<User> clientsOrderedMoreThanNrAndMoreThanSum(int times) {
        List<Client> clients = clientService.selectAll();
        List<User> users = userService.selectAll();

        List<User> clientUsers = users.stream().filter(user -> checkForUserId(user.getId(), clients)).collect(Collectors.toList());

        Map<Integer, Order> orders = orderService.selectAll();

        return clientUsers.stream()
                .filter(user -> countTimeForSingleClient(user.getId(), orders) > times)
                .collect(Collectors.toList());
    }

    public List<Order> countForSumGreaterThanNr(int sum) {
        return orderService.selectAll().values().stream().filter(order -> order.getTotalPrice() > sum).collect(Collectors.toList());
    }

    public Map<MenuItem, Integer> countForProductsInADay(String date, Map<MenuItem, Integer> times) {
        Map<Integer, Order> orderProducts = orderService.selectAll();
        List<MenuItem> products = new ArrayList<>();
        for (Order order : orderProducts.values()) {
            products.addAll(order.getMenuItems());
        }
        products.sort(new SorterByName());
        Set<MenuItem> hashString = new HashSet<>(products);
        for (MenuItem item : hashString) {
            times.put(item, giveCountForEachItem(products, item.getTitle()));
        }
        return times;
    }


    int giveCountForEachItem(List<MenuItem> products, String item) {
        int cnt = 0;
        for (MenuItem menuItem : products) {
            if (menuItem.getTitle().equals(item)) {
                cnt++;
            }
        }
        return cnt;
    }

    public int countTimeForSingleClient(int clientId, Map<Integer, Order> orders) {
        int cnt = 0;
        for (Order order : orders.values()) {
            if (order.getClientId().equals(clientId)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean checkForUserId(int userId, List<Client> clients) {
        for (Client client : clients) {
            if (client.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
