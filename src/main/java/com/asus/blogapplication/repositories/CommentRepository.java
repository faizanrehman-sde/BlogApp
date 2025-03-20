package com.asus.blogapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asus.blogapplication.Entities.Comment;
import com.asus.blogapplication.Entities.Post;
import com.asus.blogapplication.Entities.User;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByUser(User user);
	
	  @Query("SELECT COUNT (c) FROM Comment c WHERE c.user.id = :userId")
	  Long countCommentsByUserId(@Param("userId") Long userId);

}
