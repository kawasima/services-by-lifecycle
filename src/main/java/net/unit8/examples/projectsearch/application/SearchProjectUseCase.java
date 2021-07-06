package net.unit8.examples.projectsearch.application;

import net.unit8.examples.projectsearch.domain.Project;
import org.springframework.data.domain.Page;

public interface SearchProjectUseCase {
    Page<Project> search(SearchProjectCommand command);
}
