package org.safa.maintenanceservicejobservice.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mirrors UserResponse from user-service:
 * record UserResponse(long id, String fullName, String userName, String phoneNumber, Set<String> roles)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private long id;
    private String fullName;
    private String userName;
    private String phoneNumber;
}
