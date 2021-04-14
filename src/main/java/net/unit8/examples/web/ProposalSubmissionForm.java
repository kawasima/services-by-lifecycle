package net.unit8.examples.web;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProposalSubmissionForm implements Serializable {
    @NotNull
    private String projectId;

    private String note;

    @NotBlank
    private String estimationType;

    @DecimalMin("0")
    @DecimalMax("100000000")
    @NotNull(groups = { FixedEstimationGroup.class })
    private BigDecimal total;

    @NotNull(groups = { PerHourEstimationGroup.class })
    private BigDecimal unitPrice;

    @NotNull(groups = { PerHourEstimationGroup.class })
    private BigDecimal hours;
}
