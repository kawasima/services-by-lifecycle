package net.unit8.examples.proposal.selection1st.application.command;

import lombok.Value;
import net.unit8.examples.proposal.domain.ProposalId;

import java.io.Serializable;

@Value
public class Select1stRoundCommand implements Serializable {
    ProposalId proposalId;
    String reason;
}
