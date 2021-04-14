package net.unit8.examples.user.adapter.persistence.repository;

import net.unit8.examples.user.adapter.persistence.entity.ProjectOwnerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectOwnerRepository extends JpaRepository<ProjectOwnerJpaEntity, Long> {
}
