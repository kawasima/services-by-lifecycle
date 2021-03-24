package net.unit8.examples.proposal.submission.adapter.persistence;

import net.unit8.examples.proposal.submission.adapter.persistence.entity.ProposalSubmissionJpaEntity;
import net.unit8.examples.proposal.submission.adapter.persistence.mapper.SubmitProposalMapper;
import net.unit8.examples.proposal.submission.adapter.persistence.repository.ProposalSubmissionRepository;
import net.unit8.examples.proposal.submission.application.port.SaveSubmitProposalPort;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.PersistenceAdapter;

@PersistenceAdapter
public class SubmitProposalPersistenceAdapter implements SaveSubmitProposalPort {
    private final SubmitProposalMapper submitProposalMapper;
    private final ProposalSubmissionRepository proposalSubmissionRepository;

    public SubmitProposalPersistenceAdapter(SubmitProposalMapper submitProposalMapper, ProposalSubmissionRepository proposalSubmissionRepository) {
        this.submitProposalMapper = submitProposalMapper;
        this.proposalSubmissionRepository = proposalSubmissionRepository;
    }

    @Override
    public void save(SubmitProposal proposal) {
        ProposalSubmissionJpaEntity proposalSubmissionJpaEntity = submitProposalMapper.mapToEntity(proposal);
        proposalSubmissionRepository.save(proposalSubmissionJpaEntity);
    }
}
