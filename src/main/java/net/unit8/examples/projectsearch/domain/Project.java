package net.unit8.examples.projectsearch.domain;

import lombok.Value;

import java.io.Serializable;

@Value
public class Project implements Serializable {
    ProjectId id;
    String name;
    String description;
}
