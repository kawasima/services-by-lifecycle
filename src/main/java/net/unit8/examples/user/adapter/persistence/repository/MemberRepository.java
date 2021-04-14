package net.unit8.examples.user.adapter.persistence.repository;

import net.unit8.examples.user.adapter.persistence.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByEmail(String email);
}
