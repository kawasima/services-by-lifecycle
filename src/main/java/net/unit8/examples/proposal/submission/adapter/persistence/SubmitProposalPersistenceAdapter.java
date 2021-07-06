package net.unit8.examples.proposal.submission.adapter.persistence;

import net.unit8.examples.projectsearch.domain.ProjectId;
import net.unit8.examples.proposal.submission.application.GetSubmittedProposalsPort;
import net.unit8.examples.proposal.submission.application.SaveSubmitProposalPort;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.PersistenceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@PersistenceAdapter
class SubmitProposalPersistenceAdapter implements SaveSubmitProposalPort, GetSubmittedProposalsPort {
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

    @Override
    public Page<SubmitProposal> getSubmittedProposal(ProjectId projectId) {
        PageRequest pageRequest = PageRequest.of(0,10);
        return proposalSubmissionRepository.findByProjectId(projectId.getValue(), pageRequest)
                .map(submitProposalMapper::mapToDomain);
    }
}
