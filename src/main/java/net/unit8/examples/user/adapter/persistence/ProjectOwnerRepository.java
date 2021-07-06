package net.unit8.examples.user.adapter.persistence;

import net.unit8.examples.user.adapter.persistence.ProjectOwnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectOwnerRepository extends JpaRepository<ProjectOwnerJpaEntity, Long> {
}
