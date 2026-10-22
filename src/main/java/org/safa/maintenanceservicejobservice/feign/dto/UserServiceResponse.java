package org.safa.maintenanceservicejobservice.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic envelope returned by user-service, matching ApiResponse<T> shape:
 * { code, data, message }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceResponse<T> {
    private int code;
    private T data;
    private String message;
}
