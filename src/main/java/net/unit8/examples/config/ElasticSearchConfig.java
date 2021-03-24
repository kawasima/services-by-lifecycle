package net.unit8.examples.config;

import net.unit8.examples.projectsearch.adapter.persistence.entity.ProjectIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import javax.annotation.PreDestroy;

@Configuration
public class ElasticSearchConfig {
	@Autowired
	ElasticsearchOperations elasticsearchOperations;

	@PreDestroy
	public void deleteIndex() {
		elasticsearchOperations.indexOps(ProjectIndex.class).delete();
	}

}
