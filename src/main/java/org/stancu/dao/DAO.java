package org.stancu.dao;

import java.util.List;

public interface DAO<T> {

    T findById(Integer id);

    List<T> selectAll();

    T delete(Integer id);

    T update(T model, Integer id);

    T insert(T model);

    Integer getLastId();
}
