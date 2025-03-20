package com.asus.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asus.blogapplication.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
