package net.unit8.examples.draft.adapter.persistence;

import net.unit8.examples.draft.adapter.persistence.PublishedProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishedProjectRepository extends JpaRepository<PublishedProjectJpaEntity, Long> {
}
