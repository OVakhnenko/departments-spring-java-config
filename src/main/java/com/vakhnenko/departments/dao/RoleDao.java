package com.vakhnenko.departments.dao;

import com.vakhnenko.departments.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
