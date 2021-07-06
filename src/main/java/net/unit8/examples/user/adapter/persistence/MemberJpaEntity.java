package net.unit8.examples.user.adapter.persistence;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "members")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "member_type")
@Data
public class MemberJpaEntity implements Serializable {
    @Id
    private String id;
    private String email;
    private String password;
}
