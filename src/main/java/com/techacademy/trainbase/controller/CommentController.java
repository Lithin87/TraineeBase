package com.techacademy.trainbase.controller;

import com.techacademy.trainbase.dto.CommentCreateDTO;
import com.techacademy.trainbase.dto.CommentDTO;
import com.techacademy.trainbase.dto.CommentUpdateDTO;
import com.techacademy.trainbase.entity.Comment;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import com.techacademy.trainbase.exception.ResourceNotFoundException;
import com.techacademy.trainbase.mapper.CommentMapper;
import com.techacademy.trainbase.response.ApiResponse;
import com.techacademy.trainbase.service.CommentService;
import com.techacademy.trainbase.service.TaskService;
import com.techacademy.trainbase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentMapper commentMapper;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments()
            .stream()
            .map(commentMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(comments));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentDTO>> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        return ResponseEntity.ok(ApiResponse.success(commentMapper.toDTO(comment)));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<CommentDTO>> createComment(@Valid @RequestBody CommentCreateDTO commentCreateDTO) {
        Comment comment = commentMapper.toEntity(commentCreateDTO);
        
        // Set the task entity from the taskId in the DTO
        if (commentCreateDTO.getTaskId() != null) {
            Task task = taskService.getTaskById(commentCreateDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", commentCreateDTO.getTaskId()));
            comment.setTask(task);
        }
        
        // Set the user entity from the userId in the DTO
        if (commentCreateDTO.getUserId() != null) {
            User user = userService.getUserById(commentCreateDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", commentCreateDTO.getUserId()));
            comment.setUser(user);
        }
        
        Comment createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Comment created successfully", commentMapper.toDTO(createdComment)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentDTO>> updateComment(@PathVariable Long id, @Valid @RequestBody CommentUpdateDTO commentUpdateDTO) {
        Comment existingComment = commentService.getCommentById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
        
        commentMapper.updateEntityFromDTO(commentUpdateDTO, existingComment);
        
        // Set the task entity from the taskId in the DTO
        if (commentUpdateDTO.getTaskId() != null) {
            Task task = taskService.getTaskById(commentUpdateDTO.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", commentUpdateDTO.getTaskId()));
            existingComment.setTask(task);
        }
        
        // Set the user entity from the userId in the DTO
        if (commentUpdateDTO.getUserId() != null) {
            User user = userService.getUserById(commentUpdateDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", commentUpdateDTO.getUserId()));
            existingComment.setUser(user);
        }
        
        Comment updatedComment = commentService.updateComment(id, existingComment);
        
        return ResponseEntity.ok(ApiResponse.success("Comment updated successfully", commentMapper.toDTO(updatedComment)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable Long id) {
        boolean deleted = commentService.deleteComment(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("Comment deleted successfully", null));
        }
        throw new ResourceNotFoundException("Comment", "id", id);
    }
    
    @GetMapping("/task/{taskId}")
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getCommentsByTask(@PathVariable Long taskId) {
        List<CommentDTO> comments = commentService.getCommentsByTask(taskId)
            .stream()
            .map(commentMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(comments));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getCommentsByUser(@PathVariable Long userId) {
        List<CommentDTO> comments = commentService.getCommentsByUser(userId)
            .stream()
            .map(commentMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(comments));
    }
    
    @GetMapping("/task/{taskId}/ordered")
    public ResponseEntity<ApiResponse<List<CommentDTO>>> getCommentsByTaskOrdered(@PathVariable Long taskId) {
        List<CommentDTO> comments = commentService.getCommentsByTaskOrdered(taskId)
            .stream()
            .map(commentMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(comments));
    }
}
