package org.safa.maintenanceservicejobservice.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceEstimateDto {

    private Double estimatedPrice;
    private String currency;
}
