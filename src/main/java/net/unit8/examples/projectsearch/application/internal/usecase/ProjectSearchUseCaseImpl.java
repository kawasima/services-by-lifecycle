package net.unit8.examples.projectsearch.application.internal.usecase;

import net.unit8.examples.projectsearch.application.command.SearchProjectCommand;
import net.unit8.examples.projectsearch.application.domain.Project;
import net.unit8.examples.projectsearch.application.port.SearchProjectPort;
import net.unit8.examples.projectsearch.application.usecase.SearchProjectUseCase;
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
