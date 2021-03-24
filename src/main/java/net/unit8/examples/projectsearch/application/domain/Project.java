package net.unit8.examples.projectsearch.application.domain;

import lombok.Value;
import net.unit8.examples.draft.domain.DraftProjectId;

import java.io.Serializable;

@Value
public class Project implements Serializable {
    ProjectId id;
    String name;
    String description;
}
