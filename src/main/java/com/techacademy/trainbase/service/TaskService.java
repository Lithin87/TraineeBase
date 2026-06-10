package com.techacademy.trainbase.service;

import com.techacademy.trainbase.entity.Project;
import com.techacademy.trainbase.entity.Task;
import com.techacademy.trainbase.entity.User;
import com.techacademy.trainbase.repository.ProjectRepository;
import com.techacademy.trainbase.repository.TaskRepository;
import com.techacademy.trainbase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setPriority(taskDetails.getPriority());
            if (taskDetails.getAssignee() != null) {
                task.setAssignee(taskDetails.getAssignee());
            }
            if (taskDetails.getProject() != null) {
                task.setProject(taskDetails.getProject());
            }
            task.setDueDate(taskDetails.getDueDate());
            return taskRepository.save(task);
        }
        return null;
    }
    
    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Task> getTasksByProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.map(taskRepository::findByProject).orElse(List.of());
    }
    
    public List<Task> getTasksByAssignee(Long assigneeId) {
        Optional<User> assignee = userRepository.findById(assigneeId);
        return assignee.map(taskRepository::findByAssignee).orElse(List.of());
    }
    
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
    
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }
    
    public List<Task> getTasksByProjectAndStatus(Long projectId, String status) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.map(p -> taskRepository.findByProjectAndStatus(p, status)).orElse(List.of());
    }
}
