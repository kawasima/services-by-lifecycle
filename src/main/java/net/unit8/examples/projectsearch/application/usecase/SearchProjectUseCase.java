package net.unit8.examples.projectsearch.application.usecase;

import net.unit8.examples.projectsearch.application.command.SearchProjectCommand;
import net.unit8.examples.projectsearch.application.domain.Project;
import org.springframework.data.domain.Page;

public interface SearchProjectUseCase {
    Page<Project> search(SearchProjectCommand command);
}
