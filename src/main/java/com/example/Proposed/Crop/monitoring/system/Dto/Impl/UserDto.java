package com.example.Proposed.Crop.monitoring.system.Dto.Impl;

import com.example.Proposed.Crop.monitoring.system.Dto.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserStatus {
    private String user_id;
    private String email;
    private String password;
    private String role;
}
