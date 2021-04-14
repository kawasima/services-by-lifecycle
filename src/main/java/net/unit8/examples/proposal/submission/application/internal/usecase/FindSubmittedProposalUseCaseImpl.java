package net.unit8.examples.proposal.submission.application.internal.usecase;

import net.unit8.examples.proposal.submission.application.command.FindSubmittedProposalCommand;
import net.unit8.examples.proposal.submission.application.port.GetSubmittedProposalsPort;
import net.unit8.examples.proposal.submission.application.usecase.FindSubmittedProposalUseCase;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.UseCase;
import org.springframework.data.domain.Page;

@UseCase
public class FindSubmittedProposalUseCaseImpl implements FindSubmittedProposalUseCase {
    private final GetSubmittedProposalsPort getSubmittedProposalsPort;

    public FindSubmittedProposalUseCaseImpl(GetSubmittedProposalsPort getSubmittedProposalsPort) {
        this.getSubmittedProposalsPort = getSubmittedProposalsPort;
    }

    @Override
    public Page<SubmitProposal> handle(FindSubmittedProposalCommand command) {
        return getSubmittedProposalsPort.getSubmittedProposal(command.getProjectId());
    }
}
