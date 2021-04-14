package net.unit8.examples.proposal.submission.application.port;

import net.unit8.examples.projectsearch.application.domain.ProjectId;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import org.springframework.data.domain.Page;

public interface GetSubmittedProposalsPort {
    Page<SubmitProposal> getSubmittedProposal(ProjectId projectId);
}
