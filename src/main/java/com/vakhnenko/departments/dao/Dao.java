package com.vakhnenko.departments.dao;

import com.vakhnenko.departments.entity.Entity;

import java.util.List;

public interface Dao<T extends Entity> {

    void save(T o);

    void remove(int id);

    T getById(int id);

    List<T> list();

    List<T> fillDemoData();
}
