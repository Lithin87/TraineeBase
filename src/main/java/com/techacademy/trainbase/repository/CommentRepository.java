package com.techacademy.trainbase.repository;

import com.techacademy.trainbase.entity.Comment;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
    List<Comment> findByUser(User user);
    List<Comment> findByTaskOrderByCreatedAtDesc(Task task);
}
