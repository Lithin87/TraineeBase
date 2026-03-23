package com.techacademy.trainbase.mapper;

import com.techacademy.trainbase.dto.UserCreateDTO;
import com.techacademy.trainbase.dto.UserDTO;
import com.techacademy.trainbase.dto.UserUpdateDTO;
import com.techacademy.trainbase.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-23T19:38:29+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setRole( user.getRole() );
        userDTO.setCreatedAt( user.getCreatedAt() );

        return userDTO;
    }

    @Override
    public User toEntity(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userCreateDTO.getUsername() );
        user.setEmail( userCreateDTO.getEmail() );
        user.setPassword( userCreateDTO.getPassword() );
        user.setRole( userCreateDTO.getRole() );

        return user;
    }

    @Override
    public void updateEntityFromDTO(UserUpdateDTO userUpdateDTO, User user) {
        if ( userUpdateDTO == null ) {
            return;
        }

        if ( userUpdateDTO.getUsername() != null ) {
            user.setUsername( userUpdateDTO.getUsername() );
        }
        if ( userUpdateDTO.getEmail() != null ) {
            user.setEmail( userUpdateDTO.getEmail() );
        }
        if ( userUpdateDTO.getPassword() != null ) {
            user.setPassword( userUpdateDTO.getPassword() );
        }
        if ( userUpdateDTO.getRole() != null ) {
            user.setRole( userUpdateDTO.getRole() );
        }
    }
}
