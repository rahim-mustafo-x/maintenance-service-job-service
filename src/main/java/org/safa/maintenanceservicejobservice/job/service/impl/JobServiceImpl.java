package org.safa.maintenanceservicejobservice.job.service.impl;

import lombok.RequiredArgsConstructor;
import org.safa.maintenanceservicejobservice.job.model.JobStatus;
import org.safa.maintenanceservicejobservice.job.model.dto.*;
import org.safa.maintenanceservicejobservice.job.model.entity.JobEntity;
import org.safa.maintenanceservicejobservice.job.repository.JobRepository;
import org.safa.maintenanceservicejobservice.job.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private static final DateTimeFormatter CREATED_AT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final JobRepository jobRepository;

    @Override
    public JobDto createJob(CreateJobRequest request) {
        String now = LocalDateTime.now().format(CREATED_AT_FORMATTER);
        JobEntity entity = JobEntity.builder()
                .id(UUID.randomUUID().toString())
                .laborId(request.getLaborId())
                .serviceType(request.getServiceType())
                .scheduledAt(request.getScheduledAt())
                .note(request.getNote())
                .address(request.getAddress())
                .price(request.getPrice() != null ? request.getPrice() : 0.0)
                .currency(request.getCurrency() != null ? request.getCurrency() : "UZS")
                .status(JobStatus.PENDING)
                .createdAt(now)
                .build();
        JobEntity saved = jobRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public PageableResponse<JobDto> listJobs(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JobEntity> entityPage;

        if (status != null && !status.isBlank()) {
            JobStatus jobStatus;
            try {
                jobStatus = JobStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown status: " + status);
            }
            entityPage = jobRepository.findByStatus(jobStatus, pageable);
        } else {
            entityPage = jobRepository.findAll(pageable);
        }

        List<JobDto> dtos = entityPage.getContent().stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return PageableResponse.<JobDto>builder()
                .page(entityPage.getNumber())
                .size(entityPage.getSize())
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .items(dtos)
                .content(List.of())
                .build();
    }

    @Override
    public JobDto getJobById(String id) {
        JobEntity entity = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found: " + id));
        return toDto(entity);
    }

    @Override
    public JobDto updateJobStatus(String id, UpdateJobStatusRequest request) {
        JobEntity entity = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found: " + id));
        JobStatus newStatus;
        try {
            newStatus = JobStatus.valueOf(request.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown status: " + request.getStatus());
        }
        entity.setStatus(newStatus);
        JobEntity saved = jobRepository.save(entity);
        return toDto(saved);
    }

    @Override
    public PriceEstimateDto estimateJob(EstimateJobRequest request) {
        // Placeholder estimation logic: returns a fixed rate per hour.
        // Replace with real pricing logic when available.
        double ratePerHour = 50000.0;
        double estimated = ratePerHour * request.getHours();
        return PriceEstimateDto.builder()
                .estimatedPrice(estimated)
                .currency("UZS")
                .build();
    }

    // -------------------------------------------------------------------------
    // Mapping helpers
    // -------------------------------------------------------------------------

    private JobDto toDto(JobEntity e) {
        return JobDto.builder()
                .id(e.getId())
                .customerId(e.getCustomerId())
                .customerName(e.getCustomerName())
                .customerPhone(e.getCustomerPhone())
                .laborId(e.getLaborId())
                .laborName(e.getLaborName())
                .laborPhone(e.getLaborPhone())
                .serviceType(e.getServiceType())
                .scheduledAt(e.getScheduledAt())
                .status(e.getStatus() != null ? e.getStatus().name() : null)
                .note(e.getNote())
                .price(e.getPrice())
                .address(e.getAddress())
                .createdAt(e.getCreatedAt())
                .build();
    }
}
