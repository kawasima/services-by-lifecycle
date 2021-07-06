package net.unit8.examples.user.adapter.persistence;

import net.unit8.examples.user.adapter.persistence.RequesterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequesterRepository extends JpaRepository<RequesterJpaEntity, Long> {
}
