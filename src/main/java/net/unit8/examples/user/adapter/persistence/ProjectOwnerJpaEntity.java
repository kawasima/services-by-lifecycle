package net.unit8.examples.user.adapter.persistence;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_owners")
@Data
public class ProjectOwnerJpaEntity {
    @Id
    private String id;
}
