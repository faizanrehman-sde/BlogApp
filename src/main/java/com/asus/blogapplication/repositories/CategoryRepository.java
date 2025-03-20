package com.asus.blogapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asus.blogapplication.Entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
