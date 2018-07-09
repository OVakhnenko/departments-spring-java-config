package com.vakhnenko.departments.service;

import com.vakhnenko.departments.dao.DepartmentDao;
import com.vakhnenko.departments.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Transactional(value = "transactionManagerHibernate")
    public void save(Department department) {
        departmentDao.save(department);
    }

    @Transactional(value = "transactionManagerHibernate")
    public void remove(int id) {
        departmentDao.remove(id);
    }

    @Transactional(value = "transactionManagerHibernate")
    public void deleteAll() {
        departmentDao.deleteAll();
    }

    @Transactional(value = "transactionManagerHibernate")
    public Department getById(int id) {
        return departmentDao.getById(id);
    }

    @Transactional(value = "transactionManagerHibernate")
    public List<Department> list() {
        return departmentDao.list();
    }

    @Transactional(value = "transactionManagerHibernate")
    public Map<Integer, String> map() {
        return departmentDao.map();
    }

    @Transactional(value = "transactionManagerHibernate")
    public List<Department> fillDemoData() {
        return departmentDao.fillDemoData();
    }
}
