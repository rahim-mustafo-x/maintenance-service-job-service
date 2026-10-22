package org.safa.maintenanceservicejobservice.job.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimateJobRequest {

    @NotBlank
    private String jobTypeId;

    @NotNull
    private Integer hours;

    @NotBlank
    private String location;
}
