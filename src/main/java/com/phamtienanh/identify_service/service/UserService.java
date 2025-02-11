package com.phamtienanh.identify_service.service;

import com.phamtienanh.identify_service.dto.request.UserCreationRequest;
import com.phamtienanh.identify_service.dto.request.UserUpdateRequest;
import com.phamtienanh.identify_service.dto.response.UserResponse;
import com.phamtienanh.identify_service.entity.User;
import com.phamtienanh.identify_service.enums.Role;
import com.phamtienanh.identify_service.exception.AppRuntimeException;
import com.phamtienanh.identify_service.exception.ErrorCode;
import com.phamtienanh.identify_service.mapper.UserMapper;
import com.phamtienanh.identify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserMapper userMapper;

    public User createRequest (UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppRuntimeException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public UserResponse updateUser (UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("user not found !"));
        userMapper.updateUser(request,user);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser (String userID) {
        userRepository.deleteById(userID);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found !"))
        );
    }
}
