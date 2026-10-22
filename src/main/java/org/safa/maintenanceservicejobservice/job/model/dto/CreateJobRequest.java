package org.safa.maintenanceservicejobservice.job.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {

    @NotBlank
    private String laborId;

    @NotBlank
    private String serviceType;

    @NotBlank
    private String scheduledAt;

    private String note;
    private String address;

    @Builder.Default
    private Double price = 0.0;

    @Builder.Default
    private String currency = "UZS";
}
