package net.unit8.examples.proposal.submission.adapter.persistence.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "fixed_estimations")
@Data
@DiscriminatorValue("fixed")
public class FixedEstimationJpaEntity extends EstimationJpaEntity {
    private BigDecimal total;
}
