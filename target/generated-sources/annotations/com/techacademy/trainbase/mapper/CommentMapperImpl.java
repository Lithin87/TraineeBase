package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.CommentDTO;
import com.techacademy.trainbase.entity.Comment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-23T19:38:29+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentDTO toDTO(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId( comment.getId() );
        commentDTO.setContent( comment.getContent() );
        commentDTO.setTaskId( comment.getTaskId() );
        commentDTO.setUserId( comment.getUserId() );
        commentDTO.setCreatedAt( comment.getCreatedAt() );

        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setContent( commentDTO.getContent() );
        comment.setTaskId( commentDTO.getTaskId() );
        comment.setUserId( commentDTO.getUserId() );

        return comment;
    }

    @Override
    public Comment toEntityWithId(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDTO.getId() );
        comment.setContent( commentDTO.getContent() );
        comment.setTaskId( commentDTO.getTaskId() );
        comment.setUserId( commentDTO.getUserId() );

        return comment;
    }
}
