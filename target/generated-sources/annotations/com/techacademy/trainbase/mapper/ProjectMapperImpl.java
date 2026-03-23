package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.ProjectDTO;
import com.techacademy.trainbase.entity.Project;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-23T19:38:29+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public ProjectDTO toDTO(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId( project.getId() );
        projectDTO.setName( project.getName() );
        projectDTO.setDescription( project.getDescription() );
        projectDTO.setOwnerId( project.getOwnerId() );
        projectDTO.setCreatedAt( project.getCreatedAt() );
        projectDTO.setUpdatedAt( project.getUpdatedAt() );

        return projectDTO;
    }

    @Override
    public Project toEntity(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project project = new Project();

        project.setName( projectDTO.getName() );
        project.setDescription( projectDTO.getDescription() );
        project.setOwnerId( projectDTO.getOwnerId() );

        return project;
    }

    @Override
    public Project toEntityWithId(ProjectDTO projectDTO) {
        if ( projectDTO == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( projectDTO.getId() );
        project.setName( projectDTO.getName() );
        project.setDescription( projectDTO.getDescription() );
        project.setOwnerId( projectDTO.getOwnerId() );

        return project;
    }
}
