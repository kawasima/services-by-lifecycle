package net.unit8.examples.proposal.domain;

import lombok.Value;

import java.io.Serializable;

@Value
public class ProposalId implements Serializable {
    Long value;
}
