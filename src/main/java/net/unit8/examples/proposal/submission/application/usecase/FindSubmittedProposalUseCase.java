package net.unit8.examples.proposal.submission.application.usecase;

import net.unit8.examples.proposal.submission.application.command.FindSubmittedProposalCommand;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import org.springframework.data.domain.Page;

public interface FindSubmittedProposalUseCase {
    Page<SubmitProposal> handle(FindSubmittedProposalCommand command);
}
