package com.phamtienanh.identify_service.controller;


import com.phamtienanh.identify_service.dto.request.ApiResponse;
import com.phamtienanh.identify_service.dto.request.PermissionRequest;
import com.phamtienanh.identify_service.dto.response.PermissionResponse;
import com.phamtienanh.identify_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@FieldDefaults (level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .result(permissionService.create(permissionRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {

        var permissions = permissionService.getAll();

        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .result(permissions)
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable("permission") String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }

}
