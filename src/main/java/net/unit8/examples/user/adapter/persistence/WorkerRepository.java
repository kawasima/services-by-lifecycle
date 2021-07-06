package net.unit8.examples.user.adapter.persistence;

import net.unit8.examples.user.adapter.persistence.WorkerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<WorkerJpaEntity, Long> {
}
