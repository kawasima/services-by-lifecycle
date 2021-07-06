package net.unit8.examples.proposal.submission.application.impl;

import net.unit8.examples.proposal.submission.application.SubmitProposalCommand;
import net.unit8.examples.proposal.submission.application.SaveSubmitProposalPort;
import net.unit8.examples.proposal.submission.application.SubmitProposalUseCase;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.UseCase;

@UseCase
class SubmitProposalUseCaseImpl implements SubmitProposalUseCase {
    private final SaveSubmitProposalPort saveSubmitProposalPort;

    public SubmitProposalUseCaseImpl(SaveSubmitProposalPort saveSubmitProposalPort) {
        this.saveSubmitProposalPort = saveSubmitProposalPort;
    }

    @Override
    public void handle(SubmitProposalCommand command) {
        SubmitProposal submitProposal = SubmitProposal.withoutId(
                command.getProjectId(),
                command.getEstimation()
        );
        saveSubmitProposalPort.save(submitProposal);
    }
}
