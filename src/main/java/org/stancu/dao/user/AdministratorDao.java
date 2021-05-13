package org.stancu.dao.user;

import org.stancu.dao.DAO;
import org.stancu.model.userModel.Administrator;

import java.util.ArrayList;
import java.util.List;

public class AdministratorDao implements DAO<Administrator> {

    private ArrayList<Administrator> administrators = new ArrayList<>();

    @Override
    public Administrator findById(Integer id) {
        for (Administrator administrator : administrators) {
            if (administrator.getId().equals(id)) {
                return administrator;
            }
        }
        return null;
    }

    @Override
    public List<Administrator> selectAll() {
        return administrators;
    }

    @Override
    public Administrator delete(Integer id) {
        Administrator administrator = findById(id);
        if (administrator != null) {
            administrators.removeIf(client1 -> client1.equals(administrator));
            return administrator;
        } else return null;
    }

    @Override
    public Administrator update(Administrator model, Integer id) {
        Administrator administrator = findById(id);
        if (administrator != null) {
            int cnt = 0;
            for (Administrator administrator1 : administrators) {
                cnt++;
                if (administrator1.equals(administrator)) {
                    administrators.remove(administrator1);
                    administrators.add(cnt, model);
                    return model;
                }
            }
        }
        return null;
    }

    @Override
    public Administrator insert(Administrator model) {
        if (administrators.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId()+1);
        }
        administrators.add(model);
        return model;
    }

    @Override
    public Integer getLastId() {
        return administrators.get(administrators.size() - 1).getId();
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(ArrayList<Administrator> administrators) {
        this.administrators = administrators;
    }
}
