package com.phamtienanh.identify_service.dto.request;

import com.phamtienanh.identify_service.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RoleRequest {
    String name;

    String description;

    Set<String> permissions = new HashSet<>();
}
