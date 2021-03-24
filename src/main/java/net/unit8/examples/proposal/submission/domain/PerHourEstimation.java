package net.unit8.examples.proposal.submission.domain;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class PerHourEstimation implements Estimation, Serializable {
    BigDecimal unitPrice;
    BigDecimal hours;

    @Override
    public BigDecimal total() {
        return unitPrice.multiply(hours);
    }
}
