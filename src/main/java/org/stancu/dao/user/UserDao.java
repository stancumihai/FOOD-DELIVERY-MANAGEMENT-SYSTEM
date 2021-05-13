package org.stancu.dao.user;

import org.stancu.dao.DAO;
import org.stancu.model.userModel.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements DAO<User> {

    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public User findById(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        return users;
    }

    @Override
    public User delete(Integer id) {
        User user = findById(id);
        if (user != null) {
            users.removeIf(user1 -> user1.equals(user));
            return user;
        } else return null;
    }

    @Override
    public User update(User model, Integer id) {
        User user = findById(id);
        if (user != null) {
            int cnt = 0;
            for (User myUser : users) {
                cnt++;
                if (myUser.equals(user)) {
                    users.remove(myUser);
                    users.add(cnt, model);
                    return model;
                }
            }
        }
        return null;
    }

    @Override
    public User insert(User model) {
        if (users.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId() + 1);
        }
        users.add(model);
        return model;
    }

    @Override
    public Integer getLastId() {
        return users.get(users.size() - 1).getId();
    }

}
