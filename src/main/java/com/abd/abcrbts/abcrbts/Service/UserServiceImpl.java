package com.abd.abcrbts.abcrbts.Service;

import com.abd.abcrbts.abcrbts.Model.CustomUserDetails;
import com.abd.abcrbts.abcrbts.Model.Users;
import com.abd.abcrbts.abcrbts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService,UserService {

    @Autowired
    private UserRepository userRepository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepository.findByUsername(username);
        System.out.println("username = [" + username + "]");
        userOptional
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        CustomUserDetails customUserDetails = userOptional
                .map(users -> {
                    return new CustomUserDetails(users);}).get();
        return customUserDetails;
    }


    @Override
    public Users findByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }

    @Override
    public Users save(Users users) {

        users.setAttempt(0);
        users.setActive(true);
        users.setExpired(false);
        users.setLocked(false);

        users.setUsername(users.getFirstName().substring(0,2)+users.getLastName());
        users.setPassword(UUID.randomUUID().toString().substring(0,6));
        return userRepository.save(users);
    }

    @Override
    public Users edit(Users users) {
        return userRepository.save(users);
    }

    @Override
    public List<Users> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void addRole(int users, int role) {
        userRepository.addUserRole(users,role);
    }

    @Override
    public String changePassword(String newPassword, String oldPassword) {
        String message = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user=this.findByUsername(auth.getName());

        if(user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            message="success";
        } else {
            message="failed";
        }
        return message;
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}