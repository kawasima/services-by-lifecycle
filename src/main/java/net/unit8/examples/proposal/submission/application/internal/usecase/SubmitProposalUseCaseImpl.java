package net.unit8.examples.proposal.submission.application.internal.usecase;

import net.unit8.examples.proposal.submission.application.command.SubmitProposalCommand;
import net.unit8.examples.proposal.submission.application.port.SaveSubmitProposalPort;
import net.unit8.examples.proposal.submission.application.usecase.SubmitProposalUseCase;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import net.unit8.examples.stereotype.UseCase;

@UseCase
public class SubmitProposalUseCaseImpl implements SubmitProposalUseCase {
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
