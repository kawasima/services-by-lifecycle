package net.unit8.examples.user.adapter.persistence.repository;

import net.unit8.examples.user.adapter.persistence.entity.WorkerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerJpaEntity, Long> {
}
