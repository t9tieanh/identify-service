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
    User toUser (UserCreationRequest request);

    UserResponse toUserResponse (User user);

    @Mapping(target = "id", ignore = true)
    void updateUser(UserUpdateRequest request, @MappingTarget User user);
}
