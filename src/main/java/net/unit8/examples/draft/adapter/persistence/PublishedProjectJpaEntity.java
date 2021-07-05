package net.unit8.examples.draft.adapter.persistence;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "published_projects")
@Data
public class PublishedProjectJpaEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "draft_project_id")
    private DraftProjectJpaEntity draftProject;
}
