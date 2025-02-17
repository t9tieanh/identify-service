package com.phamtienanh.identify_service.controller;

import com.phamtienanh.identify_service.dto.request.ApiResponse;
import com.phamtienanh.identify_service.dto.request.UserCreationRequest;
import com.phamtienanh.identify_service.dto.request.UserUpdateRequest;
import com.phamtienanh.identify_service.dto.response.UserResponse;
import com.phamtienanh.identify_service.entity.User;
import com.phamtienanh.identify_service.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> addUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createRequest(request));

        return apiResponse;
    }


//    @Secured("ROLE_ADMIN")
    @GetMapping
    ApiResponse<List<User>> getAllUsers() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info(authentication.getName());
        authentication.getAuthorities().forEach(role -> {log.info(role.getAuthority());});

        return ApiResponse.<List<User>>builder()
                .code(200)
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userID}")
    @PostAuthorize("returnObject.username == authentication.name")
    UserResponse getUser(@PathVariable("userID") String userID) {
        return userService.getUserById(userID);
    }

    @PutMapping ()
    UserResponse updateUser(@RequestBody UserUpdateRequest request) {
        return userService.updateUser(request);
    }

    @DeleteMapping ("/{userID}")
    String deleteUser(@PathVariable("userID") String userID) {
        userService.deleteUser(userID);
        return "deleted user";
    }
}
