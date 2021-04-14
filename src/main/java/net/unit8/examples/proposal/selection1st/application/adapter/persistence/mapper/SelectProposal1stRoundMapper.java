package net.unit8.examples.proposal.selection1st.application.adapter.persistence.mapper;

import net.unit8.examples.proposal.domain.ProposalId;
import net.unit8.examples.proposal.selection1st.application.SelectProposal1stRound;
import net.unit8.examples.proposal.selection1st.application.adapter.persistence.entity.ProposalSelected1stRoundJpaEntity;

public class SelectProposal1stRoundMapper {
    public ProposalSelected1stRoundJpaEntity mapToEntity(SelectProposal1stRound selectProposal1stRound) {
        ProposalSelected1stRoundJpaEntity entity = new ProposalSelected1stRoundJpaEntity();
        // TODO The mapping of ProposalJpaEntity
        entity.setReason(selectProposal1stRound.getReason());
        return entity;
    }

    public SelectProposal1stRound mapToDomain(ProposalSelected1stRoundJpaEntity entity) {
        return SelectProposal1stRound.withId(
                new ProposalId(entity.getProposal().getId()),
                entity.getReason()
        );
    }
}
