package com.vakhnenko.departments.dao;

import com.vakhnenko.departments.entity.Department;
import com.vakhnenko.departments.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("employeeDao")
public class EmployeeDao implements Dao<Employee> {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.openSession();

        try {
            if (employee.getEmployee_id() == 0) {
                session.persist(employee);
            } else {
                session.update(employee);
            }
            session.flush();
            logger.info("Employee successfully saved. Employee name: " + employee.getName());
        } catch (ConstraintViolationException e) {
            logger.error("DEPSPRERR: Dublicate employee entry!", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.openSession();
        Employee employee = (Employee) session.get(Employee.class, new Integer(id));

        if (employee != null) {
            session.delete(employee);
            session.flush();
            session.close();
            logger.info("Employee successfully deleted. Employee details: " + employee);
        } else {
            logger.info("Employee not found!");
        }
    }

    @Override
    public Employee getById(int id) {
        Session session = this.sessionFactory.openSession();
        Employee employee = (Employee) session.get(Employee.class, new Integer(id));
        session.close();

        if (employee != null) {
            logger.info("Employee successfully loaded. Employee details: " + employee);
        } else {
            logger.info("Employee not found!");
        }

        return employee;
    }

    public int getCountOfEmployees(Employee employee) {
        int result;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("select count(*) from Employee where " +
                "department = :department and type = :type");
        query.setParameter("department", employee.getDepartment());
        query.setParameter("type", employee.getType());
        result = ((Long) query.uniqueResult()).intValue();

        session.close();
        return result;
    }

    @Override
    public List<Employee> list() {
        Session session = this.sessionFactory.openSession();
        List<Employee> result = session.createQuery("from Employee order by name").list();
        session.close();

        for (Employee employee : result) {
            logger.info("Employee list: " + employee);
        }
        return result;
    }

    public List<Employee> list(Department department) {
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("from Employee where department = :dept order by type, name");
        query.setParameter("dept", department);
        List<Employee> result = query.list();

        session.close();

        for (Employee employee : result) {
            logger.info("Employee list: " + employee);
        }
        return result;
    }

    public List<Employee> list(Employee employee) {
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("from Employee where department = :dept and age = :age and type = :type order by name");
        query.setParameter("dept", employee.getDepartment());
        query.setParameter("type", employee.getType());
        query.setParameter("age", employee.getAge());
        List<Employee> result = query.list();

        session.close();

        for (Employee emp : result) {
            logger.info("Employee list: " + emp);
        }
        return result;
    }

    @Override
    public List<Employee> fillDemoData() {
        return null;
    }
}
