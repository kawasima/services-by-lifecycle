package net.unit8.examples.proposal.submission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.unit8.examples.projectsearch.domain.ProjectId;
import net.unit8.examples.proposal.domain.ProposalId;

import java.io.Serializable;


@RequiredArgsConstructor(staticName = "withoutId")
@AllArgsConstructor(staticName = "withId")
@Getter
public class SubmitProposal implements Serializable {
    private ProposalId proposalId;
    private final ProjectId projectId;
    private final Estimation estimation;
}
