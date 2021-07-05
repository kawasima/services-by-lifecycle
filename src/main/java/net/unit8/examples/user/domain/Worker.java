package net.unit8.examples.user.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Getter
public class Worker extends Member{
    private String skill;
    private String qualification;

    public Worker(String id, String email, String password, String skill, String qualification) {
        super(id, email, password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority("worker"));
    }
}
