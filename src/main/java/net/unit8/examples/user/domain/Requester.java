package net.unit8.examples.user.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Getter
public class Requester extends Member {
    private final ProjectOwnerId projectOwnerId;

    public Requester(String id, String email, String password, ProjectOwnerId projectOwnerId) {
        super(id, email, password);
        this.projectOwnerId = projectOwnerId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("requester"));
    }
}
