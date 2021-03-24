package net.unit8.examples.proposal.submission.application.command;

import lombok.Value;
import net.unit8.examples.projectsearch.application.domain.ProjectId;
import net.unit8.examples.proposal.submission.domain.Estimation;

import java.io.Serializable;

@Value
public class SubmitProposalCommand implements Serializable {
    ProjectId projectId;
    Estimation estimation;
}
