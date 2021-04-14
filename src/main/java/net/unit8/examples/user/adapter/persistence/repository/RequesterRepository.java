package net.unit8.examples.user.adapter.persistence.repository;

import net.unit8.examples.user.adapter.persistence.entity.RequesterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequesterRepository extends JpaRepository<RequesterJpaEntity, Long> {
}
