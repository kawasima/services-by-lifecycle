package net.unit8.examples.projectsearch.adapter.persistence;

import net.unit8.examples.projectsearch.domain.Project;
import net.unit8.examples.projectsearch.application.SearchProjectPort;
import net.unit8.examples.stereotype.PersistenceAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@PersistenceAdapter
public class ProjectPersistenceAdapter implements SearchProjectPort {
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;

    public ProjectPersistenceAdapter(ProjectMapper projectMapper, ProjectRepository projectRepository) {
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<Project> search(String query) {
        PageRequest pageable = PageRequest.of(0, 10);
        if (query == null || query.isEmpty()) {
            return projectRepository.findAll(pageable)
                    .map(projectMapper::mapToDomain);
        } else {
            return projectRepository.findByQuery(query, pageable)
                    .map(projectMapper::mapToDomain);
        }
    }
}
