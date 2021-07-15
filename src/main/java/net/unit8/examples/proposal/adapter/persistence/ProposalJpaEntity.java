package net.unit8.examples.proposal.adapter.persistence;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "proposals")
@Data
public class ProposalJpaEntity implements Serializable {
    @Id
    private String id;

    @Column(name = "project_id")
    private String projectId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ProposalActivityJpaEntity> activities;

    private ProposalStatus proposalStatus;
}
