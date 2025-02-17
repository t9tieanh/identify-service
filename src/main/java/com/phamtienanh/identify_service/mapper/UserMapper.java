package com.phamtienanh.identify_service.mapper;

import com.phamtienanh.identify_service.dto.request.UserCreationRequest;
import com.phamtienanh.identify_service.dto.request.UserUpdateRequest;
import com.phamtienanh.identify_service.dto.response.UserResponse;
import com.phamtienanh.identify_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser (UserCreationRequest request);

    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse (User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateUser(UserUpdateRequest request, @MappingTarget User user);
}
