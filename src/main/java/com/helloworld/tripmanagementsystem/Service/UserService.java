package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.UserDTO;
import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import com.helloworld.tripmanagementsystem.Mapper.UserMapper;
import com.helloworld.tripmanagementsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper ) {
        this.userRepository = userRepository;
        this.userMapper=userMapper;
    }

    public String registerUser(UserDTO userDTO) {
        UserEntity user = userMapper.toUserEntity(userDTO);
        user.setPassword(userDTO.getPassword()); // encrypt password
        userRepository.save(user);
        return "User registered successfully!";
    }


    public String authenticateUser(String userName, String password) {
        UserEntity user = userRepository.findByUserName(userName);

        if (user != null && user.getPassword().equalsIgnoreCase(password)) {
            return "Success"; // generate JWT
        }
        return null; // invalid credentials
    }


}
