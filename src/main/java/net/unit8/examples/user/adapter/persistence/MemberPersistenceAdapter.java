package net.unit8.examples.user.adapter.persistence;

import net.unit8.examples.stereotype.PersistenceAdapter;
import net.unit8.examples.user.adapter.persistence.entity.RequesterJpaEntity;
import net.unit8.examples.user.adapter.persistence.entity.WorkerJpaEntity;
import net.unit8.examples.user.adapter.persistence.repository.MemberRepository;
import net.unit8.examples.user.domain.ProjectOwnerId;
import net.unit8.examples.user.domain.Requester;
import net.unit8.examples.user.domain.Worker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@PersistenceAdapter
public class MemberPersistenceAdapter implements UserDetailsService {
    private final MemberRepository memberRepository;

    public MemberPersistenceAdapter(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(entity -> {
                    if (entity instanceof RequesterJpaEntity e) {
                        return new Requester(e.getId(),
                                e.getEmail(),
                                e.getPassword(),
                                new ProjectOwnerId(e.getProjectOwner().getId()));

                    } else if (entity instanceof WorkerJpaEntity e) {
                        return new Worker(e.getId(),
                                e.getEmail(),
                                e.getPassword(),
                                e.getSkill(),
                                e.getQualification());
                    } else {
                        throw new IllegalStateException();
                    }
                })
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
