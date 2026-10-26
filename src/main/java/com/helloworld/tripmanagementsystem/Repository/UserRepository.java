package com.helloworld.tripmanagementsystem.Repository;

import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserName(String userName);
}

