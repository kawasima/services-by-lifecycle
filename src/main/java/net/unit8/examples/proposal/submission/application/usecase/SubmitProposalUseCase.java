package net.unit8.examples.proposal.submission.application.usecase;

import net.unit8.examples.proposal.submission.application.command.SubmitProposalCommand;

public interface SubmitProposalUseCase {
    void handle(SubmitProposalCommand command);
}
