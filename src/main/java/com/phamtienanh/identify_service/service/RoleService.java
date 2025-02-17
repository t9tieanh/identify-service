package com.phamtienanh.identify_service.service;


import com.phamtienanh.identify_service.dto.request.RoleRequest;
import com.phamtienanh.identify_service.dto.response.RoleResponse;
import com.phamtienanh.identify_service.entity.Role;
import com.phamtienanh.identify_service.mapper.RoleMapper;
import com.phamtienanh.identify_service.repository.PermissionRepository;
import com.phamtienanh.identify_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleResponse create (RoleRequest roleRequest) {
        try {
            var role = roleMapper.toRole(roleRequest);

            var permissionList = permissionRepository.findAllById(roleRequest.getPermissions());
            role.setPermissions(new HashSet<>(permissionList));

            return roleMapper.toRoleResponse(roleRepository.save(role));
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }

    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(
                roleMapper::toRoleResponse
        ).toList() ;
    }

    public void delete (String roleName) {
        roleRepository.deleteById(roleName);
    }


}
