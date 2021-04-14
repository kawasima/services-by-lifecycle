package net.unit8.examples.proposal.selection1st.application.adapter.persistence.repository;

import net.unit8.examples.proposal.selection1st.application.adapter.persistence.entity.ProposalSelected1stRoundJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalSelected1stRoundRepository extends JpaRepository<ProposalSelected1stRoundJpaEntity, Long> {
}
