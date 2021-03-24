package net.unit8.examples.projectsearch.application.port;

import net.unit8.examples.projectsearch.application.domain.Project;
import org.springframework.data.domain.Page;

public interface SearchProjectPort {
    Page<Project> search(String query);
}
