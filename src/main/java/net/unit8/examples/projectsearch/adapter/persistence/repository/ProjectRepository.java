package net.unit8.examples.projectsearch.adapter.persistence.repository;

import net.unit8.examples.projectsearch.adapter.persistence.entity.ProjectIndex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProjectRepository extends ElasticsearchRepository<ProjectIndex, Long> {
    @Query("{\"bool\":{\"should\":[{\"match\": {\"name\":\"?0\"}},{\"match\":{\"description\":\"?0\"}}]}}")
    Page<ProjectIndex> findByQuery(String query, Pageable pageable);
}
