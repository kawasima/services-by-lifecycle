package net.unit8.examples.projectsearch.adapter.persistence;

import am.ik.yavi.arguments.Arguments1Validator;
import am.ik.yavi.arguments.ArgumentsValidators;
import net.unit8.examples.projectsearch.domain.Project;
import net.unit8.examples.projectsearch.domain.ProjectDescription;
import net.unit8.examples.projectsearch.domain.ProjectId;
import net.unit8.examples.projectsearch.domain.ProjectName;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project mapToDomain(ProjectIndex index) {
        Arguments1Validator<ProjectIndex, Project> projectValidator = ArgumentsValidators
                .combine(
                        ProjectId.validator().compose(ProjectIndex::getId),
                        ProjectName.validator().compose(ProjectIndex::getName),
                        ProjectDescription.validator().compose(ProjectIndex::getDescription))
                .apply(Project::of);

        return projectValidator.validated(index);
    }

    public ProjectIndex mapToIndex(Project project) {
        return ProjectIndex.builder()
                .id(project.getId().getValue())
                .name(project.getName().getValue())
                .description(project.getDescription().getValue())
                .build();
    }
}
