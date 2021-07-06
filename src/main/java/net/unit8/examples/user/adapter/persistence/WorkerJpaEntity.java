package net.unit8.examples.user.adapter.persistence;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "workers")
@DiscriminatorValue("WORKER")
@Data
public class WorkerJpaEntity extends MemberJpaEntity {
    private String skill;
    private String qualification;
}
