package net.unit8.examples.projectsearch.adapter.persistence;

import net.unit8.examples.projectsearch.adapter.persistence.ProjectIndex;
import net.unit8.examples.projectsearch.domain.Project;
import net.unit8.examples.projectsearch.domain.ProjectId;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project mapToDomain(ProjectIndex index) {
        return new Project(new ProjectId(index.getId()),
                index.getName(),
                index.getDescription());
    }

    public ProjectIndex mapToIndex(Project project) {
        return ProjectIndex.builder()
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }
}
