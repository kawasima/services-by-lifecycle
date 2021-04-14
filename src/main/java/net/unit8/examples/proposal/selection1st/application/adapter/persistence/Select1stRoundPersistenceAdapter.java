package net.unit8.examples.proposal.selection1st.application.adapter.persistence;

import net.unit8.examples.proposal.selection1st.application.SelectProposal1stRound;
import net.unit8.examples.proposal.selection1st.application.adapter.persistence.repository.ProposalSelected1stRoundRepository;
import net.unit8.examples.proposal.selection1st.application.port.SaveSelect1stRoundPort;
import net.unit8.examples.stereotype.PersistenceAdapter;

@PersistenceAdapter
public class Select1stRoundPersistenceAdapter implements SaveSelect1stRoundPort {
    private final ProposalSelected1stRoundRepository proposalSelected1stRoundRepository;

    public Select1stRoundPersistenceAdapter(ProposalSelected1stRoundRepository proposalSelected1stRoundRepository) {
        this.proposalSelected1stRoundRepository = proposalSelected1stRoundRepository;
    }

    @Override
    public SelectProposal1stRound save(SelectProposal1stRound selectProposal1stRound) {
        return null;
    }
}
