package net.unit8.examples.draft.adapter.persistence;

import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.sql.Date;
import java.time.LocalDate;

public interface DraftProjectRepository extends JpaRepository<DraftProjectJpaEntity, String> {
    @Query("SELECT dp FROM DraftProjectJpaEntity dp WHERE " +
            "NOT EXISTS (select pp FROM PublishedProjectJpaEntity pp WHERE pp.draftProject.id = dp.id) " +
            "AND dp.recruitmentBeginOn <= :standardDate " +
            "AND dp.recruitmentEndOn >= :standardDate")
    Streamable<DraftProjectJpaEntity> findPublicationTargetProjects(@Param("standardDate") LocalDate standardDate);
}
