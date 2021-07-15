package net.unit8.examples.projectsearch.application.impl;

import net.unit8.examples.projectsearch.application.SearchProjectQuery;
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
    public Page<Project> search(SearchProjectQuery command) {
        return searchProjectPort.search(command.getQuery());
    }
}
