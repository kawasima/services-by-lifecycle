package net.unit8.examples.projectsearch.adapter.persistence.mapper;

import net.unit8.examples.projectsearch.adapter.persistence.entity.ProjectIndex;
import net.unit8.examples.projectsearch.application.domain.Project;
import net.unit8.examples.projectsearch.application.domain.ProjectId;
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
