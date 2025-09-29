package com.userservice.manager;

import com.userservice.dto.UserDTO;
import com.userservice.mapper.UserMapper;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserManager(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUser(String username){
        return userRepository.findByUsername(username).map(userMapper::userToUserDTO).orElse(null);
    }
    
    public String getUsernameById(Long id){
        return userRepository.findById(id).map(User::getUsername).orElse(null);
    }

    public boolean createUser(User user){
        User existingUsername = userRepository.findByUsername(user.getUsername()).orElse(null);
        User existingEmail = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(existingUsername != null || existingEmail != null){
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
