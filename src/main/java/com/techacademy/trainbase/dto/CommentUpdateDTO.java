package com.techacademy.trainbase.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentUpdateDTO {
    
    @NotBlank(message = "Comment content is required")
    private String content;
    
    private Long taskId;
    
    private Long userId;
    
    // Constructors
    public CommentUpdateDTO() {}
    
    public CommentUpdateDTO(String content, Long taskId, Long userId) {
        this.content = content;
        this.taskId = taskId;
        this.userId = userId;
    }
    
    // Getters and Setters
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Long getTaskId() {
        return taskId;
    }
    
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
