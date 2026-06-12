package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.ProjectCreateDTO;
import com.techacademy.trainbase.dto.ProjectDTO;
import com.techacademy.trainbase.dto.ProjectUpdateDTO;
import com.techacademy.trainbase.entity.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProjectMapper {
    
    @Mapping(target = "ownerId", source = "owner.id")
    ProjectDTO toDTO(Project project);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Project toEntity(ProjectDTO projectDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    Project toEntity(ProjectCreateDTO projectCreateDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    void updateEntityFromDTO(ProjectUpdateDTO projectUpdateDTO, @MappingTarget Project project);
    

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Project toEntityWithId(ProjectDTO projectDTO);
}
