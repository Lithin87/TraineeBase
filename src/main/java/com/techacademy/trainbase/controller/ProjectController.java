package com.techacademy.trainbase.controller;

import com.techacademy.trainbase.dto.ProjectCreateDTO;
import com.techacademy.trainbase.dto.ProjectDTO;
import com.techacademy.trainbase.dto.ProjectUpdateDTO;
import com.techacademy.trainbase.entity.Project;
import com.techacademy.trainbase.entity.User;
import com.techacademy.trainbase.exception.ResourceNotFoundException;
import com.techacademy.trainbase.mapper.ProjectMapper;
import com.techacademy.trainbase.response.ApiResponse;
import com.techacademy.trainbase.service.ProjectService;
import com.techacademy.trainbase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectMapper projectMapper;
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects()
            .stream()
            .map(projectMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(projects));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        return ResponseEntity.ok(ApiResponse.success(projectMapper.toDTO(project)));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> createProject(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        Project project = projectMapper.toEntity(projectCreateDTO);
        
        // Set the owner entity from the ownerId in the DTO
        if (projectCreateDTO.getOwnerId() != null) {
            User owner = userService.getUserById(projectCreateDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", projectCreateDTO.getOwnerId()));
            project.setOwner(owner);
        }
        
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success("Project created successfully", projectMapper.toDTO(createdProject)));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectUpdateDTO projectUpdateDTO) {
        Project existingProject = projectService.getProjectById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        
        projectMapper.updateEntityFromDTO(projectUpdateDTO, existingProject);
        
        // Set the owner entity from the ownerId in the DTO
        if (projectUpdateDTO.getOwnerId() != null) {
            User owner = userService.getUserById(projectUpdateDTO.getOwnerId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", projectUpdateDTO.getOwnerId()));
            existingProject.setOwner(owner);
        }
        
        Project updatedProject = projectService.updateProject(id, existingProject);
        
        return ResponseEntity.ok(ApiResponse.success("Project updated successfully", projectMapper.toDTO(updatedProject)));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id) {
        boolean deleted = projectService.deleteProject(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.success("Project deleted successfully", null));
        }
        throw new ResourceNotFoundException("Project", "id", id);
    }
    
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getProjectsByOwner(@PathVariable Long ownerId) {
        List<ProjectDTO> projects = projectService.getProjectsByOwner(ownerId)
            .stream()
            .map(projectMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(projects));
    }
    
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> searchProjectsByName(@RequestParam String name) {
        List<ProjectDTO> projects = projectService.searchProjectsByName(name)
            .stream()
            .map(projectMapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(projects));
    }
}
