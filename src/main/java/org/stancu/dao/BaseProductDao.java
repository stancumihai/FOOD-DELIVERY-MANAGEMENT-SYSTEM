package org.stancu.dao;

import org.stancu.model.MenuItem;
import org.stancu.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

public class BaseProductDao implements DAO<MenuItem> {

    private List<MenuItem> menuItems = new ArrayList<>();


    @Override
    public MenuItem findById(Integer id) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId().equals(id)) {
                return menuItem;
            }
        }
        return null;
    }

    @Override
    public List<MenuItem> selectAll() {
        return menuItems;
    }

    @Override
    public MenuItem delete(Integer id) {
        MenuItem menuItem = findById(id);
        if (menuItem != null) {
            menuItems.removeIf(baseProduct1 -> baseProduct1.equals(menuItem));
            return menuItem;
        } else return null;
    }

    @Override
    public MenuItem update(MenuItem model, Integer id) {
        MenuItem menuItem = findById(id);
        assert menuItem != null;
        assert DeliveryService.isWellFormed();
        int cnt = 0;
        for (MenuItem menuItem1 : menuItems) {
            cnt++;
            if (menuItem1.equals(menuItem)) {
                menuItems.remove(menuItem1);
                menuItems.add(cnt, model);
                return model;
            }
        }
        return null;
    }

    @Override
    public MenuItem insert(MenuItem model) {
        assert model != null;
        assert DeliveryService.isWellFormed();
        if (menuItems.size() == 0) {
            model.setId(1);
        } else {
            model.setId(getLastId() + 1);
        }
        menuItems.add(model);
        return model;
    }

    @Override
    public Integer getLastId() {
        return menuItems.get(menuItems.size() - 1).getId();
    }

    public void setBaseProducts() {
        this.menuItems = DeliveryService.getItems();
    }
}
