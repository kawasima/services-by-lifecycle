package net.unit8.examples.draft.adapter.persistence.repository;

import net.unit8.examples.draft.adapter.persistence.entity.PublishedProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishedProjectRepository extends JpaRepository<PublishedProjectJpaEntity, Long> {
}
