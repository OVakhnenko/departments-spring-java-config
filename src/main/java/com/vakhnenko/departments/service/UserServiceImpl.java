package com.vakhnenko.departments.service;

import com.vakhnenko.departments.dao.RoleDao;
import com.vakhnenko.departments.dao.UserDao;
import com.vakhnenko.departments.entity.Role;
import com.vakhnenko.departments.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vakhnenko.departments.entity.Constants.ROLE_USER;
import static com.vakhnenko.departments.entity.Constants.ROLE_USER_NAME;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(value = "transactionManager")
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<User> users = new HashSet<>();
        users.add(user);

        Role role = new Role();
        role.setId(ROLE_USER);
        role.setName(ROLE_USER_NAME);
        role.setUsers(users);

        Set<Role> roles = new HashSet<>();
        roles.add(role); // set role USER for all new users;
        user.setRoles(roles);

        userDao.save(user);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void delete(long id) {
        User user = userDao.getOne(id);
        userDao.delete(user);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User getOne(long id) {
        return userDao.findOne(id);
    }

    @Override
    @Transactional(value = "transactionManager")
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional(value = "transactionManager")
    // https://devcolibri.com/spring-data-jpa-пишем-dao-и-services-часть-2/
    public List<User> getAll() {
        return userDao.findAll(new Sort(Sort.Direction.ASC, "username"));
    }
}
