package net.unit8.examples.proposal.submission.application.command;

import lombok.Value;
import net.unit8.examples.projectsearch.application.domain.ProjectId;

import java.io.Serializable;

@Value
public class FindSubmittedProposalCommand implements Serializable {
    ProjectId projectId;
}
