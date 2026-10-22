package org.safa.maintenanceservicejobservice.feign.client;

import org.safa.maintenanceservicejobservice.feign.dto.UserInfo;
import org.safa.maintenanceservicejobservice.feign.dto.UserServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Feign client for the user-service.
 * Eureka name: USER-SERVICE  (registered by spring.application.name=user-service).
 * The gateway strips "/user-service" prefix, so the actual paths on user-service are /v1/user/{id}.
 * We call user-service directly via Eureka (no gateway prefix needed).
 */
@FeignClient(name = "USER-SERVICE", contextId = "userClient")
public interface UserFeignClient {

    /**
     * GET /v1/user/{id} → ApiResponse<UserResponse>
     * where UserResponse = { id, fullName, userName, phoneNumber, roles }
     */
    @GetMapping("/v1/user/{id}")
    UserServiceResponse<UserInfo> getUserById(
            @PathVariable("id") long id,
            @RequestHeader("Authorization") String bearerToken
    );
}
