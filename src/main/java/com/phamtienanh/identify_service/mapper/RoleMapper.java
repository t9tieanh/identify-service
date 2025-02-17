package com.phamtienanh.identify_service.mapper;

import com.phamtienanh.identify_service.dto.request.RoleRequest;
import com.phamtienanh.identify_service.dto.response.RoleResponse;
import com.phamtienanh.identify_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
}
