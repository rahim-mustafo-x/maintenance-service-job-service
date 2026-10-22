package org.safa.maintenanceservicejobservice.job.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.safa.maintenanceservicejobservice.job.model.dto.*;
import org.safa.maintenanceservicejobservice.job.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /**
     * POST /v1/jobs — Create a new job booking.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<JobDto>> createJob(
            @Valid @RequestBody CreateJobRequest request) {
        JobDto dto = jobService.createJob(request);
        return ResponseEntity.ok(ApiResponse.created(dto));
    }

    /**
     * GET /v1/jobs — List jobs, optionally filtered by status, with pagination.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageableResponse<JobDto>>> listJobs(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        PageableResponse<JobDto> result = jobService.listJobs(status, page, size);
        return ResponseEntity.ok(ApiResponse.ok(result));
    }

    /**
     * GET /v1/jobs/{id} — Get a single job by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobDto>> getJobById(@PathVariable String id) {
        JobDto dto = jobService.getJobById(id);
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    /**
     * PUT /v1/jobs/{id}/status — Update a job's status (accept, decline, complete).
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<JobDto>> updateJobStatus(
            @PathVariable String id,
            @Valid @RequestBody UpdateJobStatusRequest request) {
        JobDto dto = jobService.updateJobStatus(id, request);
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    /**
     * POST /v1/jobs/estimate — Estimate price for a job.
     */
    @PostMapping("/estimate")
    public ResponseEntity<ApiResponse<PriceEstimateDto>> estimateJob(
            @Valid @RequestBody EstimateJobRequest request) {
        PriceEstimateDto dto = jobService.estimateJob(request);
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }
}
