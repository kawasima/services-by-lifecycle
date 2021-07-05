package net.unit8.examples.draft.adapter.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "draft_projects")
@Data
public class DraftProjectJpaEntity {
    @Id
    private String id;

    @Column(name = "project_owner_id", nullable = false)
    private String projectOwnerId;

    private String name;
    private String description;
    private LocalDate recruitmentBeginOn;
    private LocalDate recruitmentEndOn;
}
