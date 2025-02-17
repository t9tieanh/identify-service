package com.phamtienanh.identify_service.mapper;
import com.phamtienanh.identify_service.dto.request.PermissionRequest;
import com.phamtienanh.identify_service.dto.response.PermissionResponse;
import com.phamtienanh.identify_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponse toPermissionResponse(Permission permission);
    Permission toPermission(PermissionRequest permissionRequest);
}
