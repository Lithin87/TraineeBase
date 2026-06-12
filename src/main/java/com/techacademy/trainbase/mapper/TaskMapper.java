package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.TaskCreateDTO;
import com.techacademy.trainbase.dto.TaskDTO;
import com.techacademy.trainbase.dto.TaskUpdateDTO;
import com.techacademy.trainbase.entity.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProjectMapper.class})
public interface TaskMapper {
    
    @Mapping(target = "assigneeId", source = "assignee.id")
    @Mapping(target = "projectId", source = "project.id")
    TaskDTO toDTO(Task task);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    Task toEntity(TaskDTO taskDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Task toEntity(TaskCreateDTO taskCreateDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateEntityFromDTO(TaskUpdateDTO taskUpdateDTO, @MappingTarget Task task);
    

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "project", ignore = true)
    Task toEntityWithId(TaskDTO taskDTO);
}
