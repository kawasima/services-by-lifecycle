package net.unit8.examples.draft.adapter.persistence.repository;

import net.unit8.examples.draft.adapter.persistence.entity.DraftProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.sql.Date;

public interface DraftProjectRepository extends JpaRepository<DraftProjectJpaEntity, Long> {
    @Query("SELECT dp FROM DraftProjectJpaEntity dp WHERE " +
            "NOT EXISTS (select pp FROM PublishedProjectJpaEntity pp WHERE pp.draftProject.id = dp.id) " +
            "AND dp.recruitmentBeginOn <= :standardDate " +
            "AND dp.recruitmentEndOn >= :standardDate")
    Streamable<DraftProjectJpaEntity> findPublicationTargetProjects(@Param("standardDate") Date standardDate);
}
