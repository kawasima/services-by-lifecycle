package net.unit8.examples.proposal.submission.application;

import lombok.Value;
import net.unit8.examples.projectsearch.domain.ProjectId;

import java.io.Serializable;

@Value
public class FindSubmittedProposalCommand implements Serializable {
    ProjectId projectId;
}
