package net.unit8.examples.user.adapter.persistence.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "workers")
@DiscriminatorValue("WORKER")
@Data
public class WorkerJpaEntity extends MemberJpaEntity {
    private String skill;
    private String qualification;
}
