package net.unit8.examples.proposal.submission.application.port;

import net.unit8.examples.proposal.submission.domain.SubmitProposal;

public interface SaveSubmitProposalPort {
    void save(SubmitProposal proposal);
}
