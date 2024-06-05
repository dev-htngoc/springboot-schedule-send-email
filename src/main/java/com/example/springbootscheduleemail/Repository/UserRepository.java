package com.example.springbootscheduleemail.Repository;

import com.example.springbootscheduleemail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByVerifyYnAndEmailSendYn(String verifyYn, String emailSendYn);
}
