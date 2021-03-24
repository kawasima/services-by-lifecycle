package net.unit8.examples.draft.adapter.persistence.mapper;

import net.unit8.examples.draft.adapter.persistence.entity.DraftProjectJpaEntity;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.draft.domain.DraftProjectId;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DraftProjectMapper {
    public DraftProject mapToDomain(DraftProjectJpaEntity entity) {
        return DraftProject.withId(
                new DraftProjectId(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                Range.closed(entity.getRecruitmentBeginOn(),
                        entity.getRecruitmentEndOn())
        );
    }

    public DraftProjectJpaEntity mapToEntity(DraftProject draftProject) {
        DraftProjectJpaEntity entity = new DraftProjectJpaEntity();
        entity.setName(draftProject.getName());
        entity.setDescription(draftProject.getDescription());
        draftProject.getRecruitmentPeriod()
                .getLowerBound()
                .getValue()
                .ifPresent(d -> entity.setRecruitmentBeginOn(new Date(d.getTime())));
        draftProject.getRecruitmentPeriod()
                .getUpperBound()
                .getValue()
                .ifPresent(d -> entity.setRecruitmentEndOn(new Date(d.getTime())));

        return entity;
    }
}
