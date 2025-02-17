package com.phamtienanh.identify_service.dto.request;

import com.phamtienanh.identify_service.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    private String password;

    private String firstName;

    private String lastName;

    private LocalDateTime dob;

    private String id;

    Set<String> roles;

}
