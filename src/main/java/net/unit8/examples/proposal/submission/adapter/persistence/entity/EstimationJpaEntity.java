package net.unit8.examples.proposal.submission.adapter.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "estimations")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "estimation_type")
public class EstimationJpaEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}
