package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.TaskDTO;
import com.techacademy.trainbase.entity.Task;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-23T19:38:29+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO toDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setTitle( task.getTitle() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setStatus( task.getStatus() );
        taskDTO.setPriority( task.getPriority() );
        taskDTO.setAssigneeId( task.getAssigneeId() );
        taskDTO.setProjectId( task.getProjectId() );
        taskDTO.setDueDate( task.getDueDate() );
        taskDTO.setCreatedAt( task.getCreatedAt() );
        taskDTO.setUpdatedAt( task.getUpdatedAt() );

        return taskDTO;
    }

    @Override
    public Task toEntity(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );
        task.setStatus( taskDTO.getStatus() );
        task.setPriority( taskDTO.getPriority() );
        task.setAssigneeId( taskDTO.getAssigneeId() );
        task.setProjectId( taskDTO.getProjectId() );
        task.setDueDate( taskDTO.getDueDate() );

        return task;
    }

    @Override
    public Task toEntityWithId(TaskDTO taskDTO) {
        if ( taskDTO == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDTO.getId() );
        task.setTitle( taskDTO.getTitle() );
        task.setDescription( taskDTO.getDescription() );
        task.setStatus( taskDTO.getStatus() );
        task.setPriority( taskDTO.getPriority() );
        task.setAssigneeId( taskDTO.getAssigneeId() );
        task.setProjectId( taskDTO.getProjectId() );
        task.setDueDate( taskDTO.getDueDate() );

        return task;
    }
}
