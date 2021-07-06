package net.unit8.examples.projectsearch.adapter.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "project")
public class ProjectIndex {
    @Id
    private String id;
    private String name;
    @Field(type = FieldType.Text)
    private String description;
}
