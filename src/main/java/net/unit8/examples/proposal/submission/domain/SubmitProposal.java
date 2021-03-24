package net.unit8.examples.proposal.submission.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.unit8.examples.projectsearch.application.domain.Project;
import net.unit8.examples.projectsearch.application.domain.ProjectId;
import net.unit8.examples.proposal.domain.ProposalId;
import org.springframework.lang.Nullable;

import java.io.Serializable;


@RequiredArgsConstructor(staticName = "withoutId")
@AllArgsConstructor(staticName = "withId")
@Getter
public class SubmitProposal implements Serializable {
    private ProposalId proposalId;
    private final ProjectId projectId;
    private final Estimation estimation;
}
