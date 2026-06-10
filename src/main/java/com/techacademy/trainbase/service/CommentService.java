package com.techacademy.trainbase.service;

import com.techacademy.trainbase.dto.CommentDTO;
import com.techacademy.trainbase.entity.Comment;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import com.techacademy.trainbase.mapper.CommentMapper;
import com.techacademy.trainbase.repository.CommentRepository;
import com.techacademy.trainbase.repository.TaskRepository;
import com.techacademy.trainbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private CommentMapper commentMapper;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }
    
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
    public Comment updateComment(Long id, Comment commentDetails) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            comment.setContent(commentDetails.getContent());
            if (commentDetails.getTask() != null) {
                comment.setTask(commentDetails.getTask());
            }
            if (commentDetails.getUser() != null) {
                comment.setUser(commentDetails.getUser());
            }
            return commentRepository.save(comment);
        }
        return null;
    }
    
    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Comment> getCommentsByTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(commentRepository::findByTask).orElse(List.of());
    }
    
    public List<Comment> getCommentsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(commentRepository::findByUser).orElse(List.of());
    }
    
    public List<Comment> getCommentsByTaskOrdered(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(commentRepository::findByTaskOrderByCreatedAtDesc).orElse(List.of());
    }
}
