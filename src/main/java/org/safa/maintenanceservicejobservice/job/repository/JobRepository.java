package org.safa.maintenanceservicejobservice.job.repository;

import org.safa.maintenanceservicejobservice.job.model.JobStatus;
import org.safa.maintenanceservicejobservice.job.model.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobEntity, String> {

    Page<JobEntity> findByStatus(JobStatus status, Pageable pageable);
}
