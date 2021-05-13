package org.stancu.service.user;

import org.stancu.dao.user.UserDao;
import org.stancu.model.userModel.User;

import java.util.List;

/***
 * The business logic for the User Class
 */
public class UserService {

    private static UserService userService = null;
    public static UserDao userDataAccessService = new UserDao();

    private UserService(UserDao userDataAccessService) {
        UserService.userDataAccessService = userDataAccessService;
    }

    /***
     *
     * @return it returns singleton instance of UserService
     */
    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(userDataAccessService);
        }
        return userService;
    }

    public User findById(Integer id) {
        return userDataAccessService.findById(id);
    }

    public User findByEmail(String email) {
        return userDataAccessService.findByEmail(email);
    }

    public User update(Integer id, User model) {
        return userDataAccessService.update(model, id);
    }

    public void insert(User model) {
        userDataAccessService.insert(model);
    }

    public User delete(Integer id) {
        return userDataAccessService.delete(id);
    }

    public List<User> selectAll() {
        return userDataAccessService.selectAll();
    }

    public Integer getLastId() {
        return userDataAccessService.getLastId();
    }

//    public User getUserByEmail(String email) {
//        return selectAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow();
//    }
}
