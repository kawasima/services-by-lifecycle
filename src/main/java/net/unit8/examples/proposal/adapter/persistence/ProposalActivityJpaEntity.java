package net.unit8.examples.proposal.adapter.persistence;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "proposal_activities")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "proposal_status")
@Data
public class ProposalActivityJpaEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "proposal_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private ProposalJpaEntity proposal;

    @Column(name = "acted_at")
    private Timestamp actedAt;
}
