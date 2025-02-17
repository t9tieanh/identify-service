package com.phamtienanh.identify_service.service;


import com.phamtienanh.identify_service.dto.request.PermissionRequest;
import com.phamtienanh.identify_service.dto.response.PermissionResponse;
import com.phamtienanh.identify_service.mapper.PermissionMapper;
import com.phamtienanh.identify_service.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;

    public PermissionResponse create (PermissionRequest permissionRequest){
        return permissionMapper.toPermissionResponse(
                permissionRepository.save(
                        permissionMapper.toPermission(permissionRequest)
                )
        );
    }

    public void delete (String permissionName) {
        permissionRepository.deleteById(permissionName);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();

        return
            permissions.stream().map(
                    permissionMapper::toPermissionResponse
            ).toList();
    }

}
