package net.unit8.examples.proposal.submission.application;

import net.unit8.examples.proposal.submission.application.SubmitProposalCommand;

public interface SubmitProposalUseCase {
    void handle(SubmitProposalCommand command);
}
