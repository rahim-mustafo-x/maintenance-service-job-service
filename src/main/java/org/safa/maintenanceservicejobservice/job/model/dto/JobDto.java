package org.safa.maintenanceservicejobservice.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    private String id;
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String laborId;
    private String laborName;
    private String laborPhone;
    private String serviceType;
    private String scheduledAt;
    private String status;
    private String note;
    private Double price;
    private String address;
    private String createdAt;
}
