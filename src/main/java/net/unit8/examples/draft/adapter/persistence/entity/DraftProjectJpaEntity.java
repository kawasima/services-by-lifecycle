package net.unit8.examples.draft.adapter.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "draft_projects")
@Data
public class DraftProjectJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Date recruitmentBeginOn;
    private Date recruitmentEndOn;
}
