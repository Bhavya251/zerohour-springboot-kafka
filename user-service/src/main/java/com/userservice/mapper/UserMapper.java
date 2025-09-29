package com.userservice.mapper;

import com.userservice.dto.UserDTO;
import com.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO userToUserDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setProfilePicture(user.getProfilePicture().isEmpty() ? "" : user.getProfilePicture());

        return userDTO;
    }
}
