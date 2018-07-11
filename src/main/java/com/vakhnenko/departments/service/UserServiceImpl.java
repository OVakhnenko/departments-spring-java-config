package com.vakhnenko.departments.service;

import com.vakhnenko.departments.dao.RoleDao;
import com.vakhnenko.departments.dao.UserDao;
import com.vakhnenko.departments.entity.Constants;
import com.vakhnenko.departments.entity.Role;
import com.vakhnenko.departments.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(value = "transactionManagerJpa")
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<User> users = new HashSet<>();
        users.add(user);

        Role role = new Role();
        role.setId(Constants.ROLE_USER);
        role.setName("ROLE_USER");
        role.setUsers(users);

        Set<Role> roles = new HashSet<>();
        roles.add(role); // set role USER for all new users;
        user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    @Transactional(value = "transactionManagerJpa")
    public void delete(long id) {
        User user = userDao.getOne(id);
        userDao.delete(user);
    }

    @Override
    @Transactional(value = "transactionManagerJpa")
    public User getOne(long id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional(value = "transactionManagerJpa")
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(value = "transactionManagerJpa")
    // https://devcolibri.com/spring-data-jpa-пишем-dao-и-services-часть-2/
    public List<User> getAll() {
        return userDao.findAll();
    }
}
