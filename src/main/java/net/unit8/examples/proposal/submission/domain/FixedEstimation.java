package net.unit8.examples.proposal.submission.domain;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class FixedEstimation implements Estimation, Serializable {
    BigDecimal total;

    @Override
    public BigDecimal total() {
        return total;
    }
}
