package com.helloworld.tripmanagementsystem.Service;

import com.helloworld.tripmanagementsystem.DTO.UserDTO;
import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import com.helloworld.tripmanagementsystem.Mapper.UserMapper;
import com.helloworld.tripmanagementsystem.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser() {

        UserDTO dto = new UserDTO();
        dto.setUserName("Hari");
        dto.setPassword("123");
        dto.setRole("User");

        UserEntity entity = new UserEntity();

        when(userMapper.toUserEntity(dto)).thenReturn(entity);

        String result = userService.registerUser(dto);

        verify(userRepository).save(entity);

        assertEquals("User registered successfully!", result);
    }

    @Test
    void testAuthenticateUserSuccess() {

        UserEntity entity = new UserEntity();
        entity.setUserName("Hari");
        entity.setPassword("123");

        when(userRepository.findByUserName("Hari")).thenReturn(entity);

        String result = userService.authenticateUser("Hari","123");

        assertEquals("Success", result);
    }

    @Test
    void testAuthenticateUserFailure() {

        when(userRepository.findByUserName("Hari")).thenReturn(null);

        String result = userService.authenticateUser("Hari","123");

        assertNull(result);
    }
}