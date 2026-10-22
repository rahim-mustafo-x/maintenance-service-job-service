package org.safa.maintenanceservicejobservice.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mirrors LaborResponse from user-service:
 * LaborResponse(id, userId, fullName, laborTypes, workingHours, averageRating)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LaborInfo {
    private Long id;
    private Long userId;
    private String fullName;
}
