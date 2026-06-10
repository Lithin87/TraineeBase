package com.techacademy.trainbase.controller;

import com.techacademy.trainbase.dto.TaskCreateDTO;
import com.techacademy.trainbase.dto.TaskDTO;
import com.techacademy.trainbase.dto.TaskUpdateDTO;
import com.techacademy.trainbase.entity.Project;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import com.techacademy.trainbase.exception.ResourceNotFoundException;
import com.techacademy.trainbase.mapper.TaskMapper;
import com.techacademy.trainbase.response.ApiResponse;
import com.techacademy.trainbase.service.ProjectService;
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
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskMapper taskMapper;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectService projectService;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks()
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        return ResponseEntity.ok(ApiResponse.success(taskMapper.toDTO(task)));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<TaskDTO>> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {
        Task task = taskMapper.toEntity(taskCreateDTO);
        
        // Set the project entity from the projectId in the DTO
        if (taskCreateDTO.getProjectId() != null) {
            Project project = projectService.getProjectById(taskCreateDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", taskCreateDTO.getProjectId()));
            task.setProject(project);
        }
        
        // Set the assignee entity from the assigneeId in the DTO
        if (taskCreateDTO.getAssigneeId() != null) {
            User assignee = userService.getUserById(taskCreateDTO.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", taskCreateDTO.getAssigneeId()));
            task.setAssignee(assignee);
        }
        
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Task created successfully", taskMapper.toDTO(createdTask)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskDTO>> updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
        Task existingTask = taskService.getTaskById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
        
        taskMapper.updateEntityFromDTO(taskUpdateDTO, existingTask);
        
        // Set the project entity from the projectId in the DTO
        if (taskUpdateDTO.getProjectId() != null) {
            Project project = projectService.getProjectById(taskUpdateDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", taskUpdateDTO.getProjectId()));
            existingTask.setProject(project);
        }
        
        // Set the assignee entity from the assigneeId in the DTO
        if (taskUpdateDTO.getAssigneeId() != null) {
            User assignee = userService.getUserById(taskUpdateDTO.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", taskUpdateDTO.getAssigneeId()));
            existingTask.setAssignee(assignee);
        }
        
        Task updatedTask = taskService.updateTask(id, existingTask);
        
        return ResponseEntity.ok(ApiResponse.success("Task updated successfully", taskMapper.toDTO(updatedTask)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("Task deleted successfully", null));
        }
        throw new ResourceNotFoundException("Task", "id", id);
    }
    
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByProject(@PathVariable Long projectId) {
        List<TaskDTO> tasks = taskService.getTasksByProject(projectId)
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
    
    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByAssignee(@PathVariable Long assigneeId) {
        List<TaskDTO> tasks = taskService.getTasksByAssignee(assigneeId)
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByStatus(@PathVariable String status) {
        List<TaskDTO> tasks = taskService.getTasksByStatus(status)
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
    
    @GetMapping("/priority/{priority}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByPriority(@PathVariable String priority) {
        List<TaskDTO> tasks = taskService.getTasksByPriority(priority)
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
    
    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<ApiResponse<List<TaskDTO>>> getTasksByProjectAndStatus(
            @PathVariable Long projectId, 
            @PathVariable String status) {
        List<TaskDTO> tasks = taskService.getTasksByProjectAndStatus(projectId, status)
            .stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(tasks));
    }
}
