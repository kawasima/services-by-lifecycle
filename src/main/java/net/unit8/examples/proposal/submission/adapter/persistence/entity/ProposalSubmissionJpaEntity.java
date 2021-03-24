package net.unit8.examples.proposal.submission.adapter.persistence.entity;

import lombok.Data;
import net.unit8.examples.proposal.adapter.persistence.entity.ProposalActivityJpaEntity;

import javax.persistence.*;

@Entity
@Table(name = "proposal_submissions")
@Data
@DiscriminatorValue("submit")
public class ProposalSubmissionJpaEntity extends ProposalActivityJpaEntity {
    @OneToOne(cascade = CascadeType.ALL)
    private EstimationJpaEntity estimation;
}