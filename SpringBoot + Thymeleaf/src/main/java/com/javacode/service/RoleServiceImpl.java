package com.javacode.service;

import com.javacode.model.Role;
import com.javacode.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }
}
