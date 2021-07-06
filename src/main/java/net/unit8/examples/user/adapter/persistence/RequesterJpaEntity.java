package net.unit8.examples.user.adapter.persistence;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "requesters")
@DiscriminatorValue("REQUESTER")
@Data
public class RequesterJpaEntity extends MemberJpaEntity {
    @OneToOne
    @JoinColumn(name = "project_owner_id")
    private ProjectOwnerJpaEntity projectOwner;
}
