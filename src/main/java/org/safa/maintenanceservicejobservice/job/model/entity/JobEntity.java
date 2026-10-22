package org.safa.maintenanceservicejobservice.job.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.safa.maintenanceservicejobservice.job.model.JobStatus;

@Entity
@Table(name = "job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    private String id;

    private String customerId;
    private String customerName;
    private String customerPhone;

    private String laborId;
    private String laborName;
    private String laborPhone;

    private String serviceType;
    private String scheduledAt;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private String note;
    private Double price;
    private String currency;
    private String address;
    private String createdAt;
}
