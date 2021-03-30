package net.unit8.examples.proposal.submission.adapter.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "per_hour_estimations")
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("per_hour")
public class PerHourEstimationJpaEntity extends EstimationJpaEntity {
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private BigDecimal hours;
}
