package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.Role;
import com.abd.abcrbts.abcrbts.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
