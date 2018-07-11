package com.vakhnenko.departments.dao;

import com.vakhnenko.departments.entity.Department;
import com.vakhnenko.departments.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("departmentDao")
public class DepartmentDao implements Dao<Department> {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Department department) {
        Session session = sessionFactory.openSession();

        try {
            if (department.getDepartment_id() == 0) {
                session.persist(department);
            } else {
                session.update(department);
            }
            session.flush();
            logger.info("Department successfully saved. Department name: " + department.getName());
        } catch (ConstraintViolationException e) {
            logger.error("DEPSPRERR: Dublicate department entry!", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.openSession();
        Department department = (Department) session.get(Department.class, new Integer(id));

        if (department != null) {
            session.delete(department);
            session.flush();
            session.close();
            logger.info("Department successfully deleted. Department details: " + department);
        } else {
            logger.info("Department not found!");
        }
    }

    public void deleteAll() {
        Session session = this.sessionFactory.openSession();

        session.createQuery("delete from Employee").executeUpdate();
        session.createQuery("delete from Department").executeUpdate();
        session.close();
    }

    @Override
    public Department getById(int id) {
        Session session = this.sessionFactory.openSession();
        Department department = (Department) session.get(Department.class, new Integer(id));
        session.close();

        if (department != null) {
            logger.info("Department successfully loaded. Department details: " + department);
        } else {
            logger.info("Department not found!");
        }

        return department;
    }

    @Override
    public List<Department> list() {
        Session session = this.sessionFactory.openSession();
        List<Department> result = session.createQuery("from Department order by name").list();
        session.close();

        for (Department department : result) {
            logger.info("Department list: " + department);
        }
        return result;
    }

    public Map<Integer, String> map() {
        Map<Integer, String> result = new LinkedHashMap<>();
        Session session = this.sessionFactory.openSession();
        List<Department> departments = session.createQuery("from Department order by name").list();
        session.close();

        for (Department department : departments) {
            result.put(department.getDepartment_id(), department.getName());
            logger.info("Department list: " + department);
        }
        return result;
    }

    @Override
    public List<Department> fillDemoData() {
        Department department;
        Set<Employee> employees;

        department = new Department("Department Kiev");
        employees = new HashSet<>();
        department.setEmployees(employees);
        employees.add(new Employee("Manager M1 kie", "M", 21, "", "Meth1", department));
        employees.add(new Employee("Developer D1 kie", "D", 21, "Lang1", "", department));
        employees.add(new Employee("Developer D2 kie", "D", 21, "Lang1", "", department));
        employees.add(new Employee("Developer D3 kie", "D", 21, "Lang1", "", department));
        save(department);

        department = new Department("Department Kharkov");
        employees = new HashSet<>();
        department.setEmployees(employees);
        employees.add(new Employee("Manager M1 kha", "M", 22, "", "Meth2", department));
        employees.add(new Employee("Manager M2 kha", "M", 22, "", "Meth2", department));
        employees.add(new Employee("Developer D1 kha", "D", 22, "Lang2", "", department));
        employees.add(new Employee("Developer D2 kha", "D", 22, "Lang2", "", department));
        employees.add(new Employee("Developer D3 kha", "D", 22, "Lang2", "", department));
        employees.add(new Employee("Developer D4 kha", "D", 22, "Lang2", "", department));
        save(department);

        department = new Department("Department Odessa");
        employees = new HashSet<>();
        department.setEmployees(employees);
        employees.add(new Employee("Manager M1 ode", "M", 23, "", "Meth3", department));
        employees.add(new Employee("Manager M2 ode", "M", 23, "", "Meth3", department));
        employees.add(new Employee("Manager M3 ode", "M", 23, "", "Meth3", department));
        employees.add(new Employee("DeveloperD1 ode", "D", 23, "Lang3", "", department));
        employees.add(new Employee("DeveloperD2 ode", "D", 23, "Lang3", "", department));
        employees.add(new Employee("DeveloperD3 ode", "D", 24, "Lang3", "", department));
        employees.add(new Employee("DeveloperD4 ode", "D", 24, "Lang3", "", department));
        employees.add(new Employee("DeveloperD5 ode", "D", 24, "Lang3", "", department));
        save(department);

        logger.info("Departments has been filled demonstartion data.");
        return list();
    }
}
