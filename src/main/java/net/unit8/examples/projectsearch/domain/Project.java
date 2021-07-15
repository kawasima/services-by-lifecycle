package net.unit8.examples.projectsearch.domain;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Project implements Serializable {
    ProjectId id;
    ProjectName name;
    ProjectDescription description;

    protected static final Validator<Project> validator = ValidatorBuilder.<Project>of()
            .constraintOnObject(Project::getId, "id", c -> c.notNull())
            .constraintOnObject(Project::getName, "name", c -> c.notNull())
            .constraintOnObject(Project::getDescription, "description", c -> c.notNull())
            .build();

    public static Project of(ProjectId id, ProjectName name, ProjectDescription description) {
        return validator.applicative().validated(new Project(id, name, description));
    }
}
