package net.unit8.examples.proposal.submission.adapter.persistence.repository;

import net.unit8.examples.proposal.submission.adapter.persistence.entity.ProposalSubmissionJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProposalSubmissionRepository extends JpaRepository<ProposalSubmissionJpaEntity, Long> {
    @Query("SELECT s FROM ProposalSubmissionJpaEntity s JOIN s.proposal p WHERE p.projectId = :projectId")
    Page<ProposalSubmissionJpaEntity> findByProjectId(@Param("projectId") String projectId, Pageable pageable);
}
