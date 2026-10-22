package org.safa.maintenanceservicejobservice.feign.client;

import org.safa.maintenanceservicejobservice.feign.dto.LaborInfo;
import org.safa.maintenanceservicejobservice.feign.dto.UserServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Feign client for the labor endpoints that live inside user-service.
 * Eureka name: USER-SERVICE (same service, different contextId to avoid bean collision).
 * Path: GET /v1/labor/{id} → ApiResponse<LaborResponse>
 * where LaborResponse = { id, userId, fullName, laborTypes, workingHours, averageRating }
 */
@FeignClient(name = "USER-SERVICE", contextId = "laborClient")
public interface LaborFeignClient {

    @GetMapping("/v1/labor/{id}")
    UserServiceResponse<LaborInfo> getLaborById(
            @PathVariable("id") long id,
            @RequestHeader("Authorization") String bearerToken
    );
}
