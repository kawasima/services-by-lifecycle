package net.unit8.examples.proposal.selection1st.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.unit8.examples.proposal.domain.ProposalId;

import java.io.Serializable;

@RequiredArgsConstructor(staticName = "withoutId")
@AllArgsConstructor(staticName = "withId")
@Getter
public class SelectProposal1stRound implements Serializable {
    ProposalId proposalId;
    final String reason;
}
