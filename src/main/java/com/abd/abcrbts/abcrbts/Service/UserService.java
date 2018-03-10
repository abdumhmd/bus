package com.abd.abcrbts.abcrbts.Service;



import com.abd.abcrbts.abcrbts.Model.Users;

import java.util.List;

public interface UserService {
    public Users findByUsername(String username);
    public Users save(Users users);
    public Users edit (Users users);
    public List<Users> findUsers();
    public Users findById(int id);
    public void addRole(int users,int role);
    public String changePassword(String newPassword, String oldPassword);
    public void delete(int id);
}