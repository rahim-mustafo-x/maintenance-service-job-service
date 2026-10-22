package org.safa.maintenanceservicejobservice.job.service;

import org.safa.maintenanceservicejobservice.job.model.dto.*;

public interface JobService {

    JobDto createJob(CreateJobRequest request);

    PageableResponse<JobDto> listJobs(String status, int page, int size);

    JobDto getJobById(String id);

    JobDto updateJobStatus(String id, UpdateJobStatusRequest request);

    PriceEstimateDto estimateJob(EstimateJobRequest request);
}
