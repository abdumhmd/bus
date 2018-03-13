package com.abd.abcrbts.abcrbts.Repository;


import com.abd.abcrbts.abcrbts.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
    Users save(Users users);
    Users findById(int id);
    List<Users> findAll();
    Users findUsersByUsername(String username);

    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO user_role VALUES (?,?)", nativeQuery = true)
    @Transactional
    void addUserRole(int user, int role);



}