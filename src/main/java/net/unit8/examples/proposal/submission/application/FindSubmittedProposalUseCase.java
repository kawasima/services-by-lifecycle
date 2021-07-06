package net.unit8.examples.proposal.submission.application;

import net.unit8.examples.proposal.submission.application.FindSubmittedProposalCommand;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import org.springframework.data.domain.Page;

public interface FindSubmittedProposalUseCase {
    Page<SubmitProposal> handle(FindSubmittedProposalCommand command);
}
