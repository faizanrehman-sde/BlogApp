package com.asus.blogapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asus.blogapplication.Entities.Category;
import com.asus.blogapplication.Entities.Post;
import com.asus.blogapplication.Entities.User;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> { 

//  custom finder methods
	Page<Post> findByUser(User user, Pageable pageable);
	Page<Post> findBycategory(Category category, Pageable p);
	Optional<List<Post>> findByTitle (String title);
	//Page<Post> findAll(Pageable p);
}
