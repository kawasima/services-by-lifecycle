package net.unit8.examples.draft.adapter.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "draft_projects")
@Data
public class DraftProjectJpaEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "project_owner_id", nullable = false)
    private Long projectOwnerId;

    private String name;
    private String description;
    private Date recruitmentBeginOn;
    private Date recruitmentEndOn;
}
