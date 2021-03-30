package net.unit8.examples.proposal.submission.adapter.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "fixed_estimations")
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("fixed")
public class FixedEstimationJpaEntity extends EstimationJpaEntity {
    private BigDecimal total;
}
