package net.unit8.examples.proposal.submission.application.impl;

import net.unit8.examples.proposal.submission.application.FindSubmittedProposalCommand;
import net.unit8.examples.proposal.submission.application.GetSubmittedProposalsPort;
import net.unit8.examples.proposal.submission.application.FindSubmittedProposalUseCase;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.UseCase;
import org.springframework.data.domain.Page;

@UseCase
class FindSubmittedProposalUseCaseImpl implements FindSubmittedProposalUseCase {
    private final GetSubmittedProposalsPort getSubmittedProposalsPort;

    public FindSubmittedProposalUseCaseImpl(GetSubmittedProposalsPort getSubmittedProposalsPort) {
        this.getSubmittedProposalsPort = getSubmittedProposalsPort;
    }

    @Override
    public Page<SubmitProposal> handle(FindSubmittedProposalCommand command) {
        return getSubmittedProposalsPort.getSubmittedProposal(command.getProjectId());
    }
}
