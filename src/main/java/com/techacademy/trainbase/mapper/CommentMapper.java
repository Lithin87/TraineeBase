package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.CommentCreateDTO;
import com.techacademy.trainbase.dto.CommentDTO;
import com.techacademy.trainbase.dto.CommentUpdateDTO;
import com.techacademy.trainbase.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserMapper.class})
public interface CommentMapper {
    
    @Mapping(target = "taskId", source = "task.id")
    @Mapping(target = "userId", source = "user.id")
    CommentDTO toDTO(Comment comment);
    
    List<CommentDTO> toDTOList(List<Comment> comments);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntity(CommentCreateDTO commentCreateDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO(CommentUpdateDTO commentUpdateDTO, @MappingTarget Comment comment);
    

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toEntityWithId(CommentDTO commentDTO);
}
