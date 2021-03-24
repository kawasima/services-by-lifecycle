package net.unit8.examples.proposal.submission.adapter.persistence.repository;

import net.unit8.examples.proposal.submission.adapter.persistence.entity.ProposalSubmissionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalSubmissionRepository extends JpaRepository<ProposalSubmissionJpaEntity, Long> {
}
