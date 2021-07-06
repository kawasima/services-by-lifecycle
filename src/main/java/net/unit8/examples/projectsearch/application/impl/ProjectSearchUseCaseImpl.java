package net.unit8.examples.projectsearch.application.impl;

import net.unit8.examples.projectsearch.application.SearchProjectCommand;
import net.unit8.examples.projectsearch.domain.Project;
import net.unit8.examples.projectsearch.application.SearchProjectPort;
import net.unit8.examples.projectsearch.application.SearchProjectUseCase;
import net.unit8.examples.stereotype.UseCase;
import org.springframework.data.domain.Page;

@UseCase
public class ProjectSearchUseCaseImpl implements SearchProjectUseCase {
    private final SearchProjectPort searchProjectPort;

    public ProjectSearchUseCaseImpl(SearchProjectPort searchProjectPort) {
        this.searchProjectPort = searchProjectPort;
    }

    @Override
    public Page<Project> search(SearchProjectCommand command) {
        return searchProjectPort.search(command.getQuery());
    }
}
