package com.phamtienanh.identify_service.controller;


import com.phamtienanh.identify_service.dto.request.ApiResponse;
import com.phamtienanh.identify_service.dto.request.PermissionRequest;
import com.phamtienanh.identify_service.dto.request.RoleRequest;
import com.phamtienanh.identify_service.dto.response.PermissionResponse;
import com.phamtienanh.identify_service.dto.response.RoleResponse;
import com.phamtienanh.identify_service.service.PermissionService;
import com.phamtienanh.identify_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .result(roleService.create(roleRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {

        var rolesList = roleService.getAll();

        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .result(rolesList)
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable("role") String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}
