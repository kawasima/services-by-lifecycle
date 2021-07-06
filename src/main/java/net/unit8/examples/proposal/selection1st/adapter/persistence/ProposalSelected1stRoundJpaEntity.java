package net.unit8.examples.proposal.selection1st.adapter.persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.unit8.examples.proposal.adapter.persistence.ProposalActivityJpaEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "proposal_selected_1st_round")
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("select_1st_round")
public class ProposalSelected1stRoundJpaEntity extends ProposalActivityJpaEntity {
    private String reason;
}
