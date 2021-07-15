package net.unit8.examples.draft.adapter.persistence;

import am.ik.yavi.arguments.Arguments;
import am.ik.yavi.arguments.Arguments1Validator;
import am.ik.yavi.arguments.ArgumentsValidators;
import net.unit8.examples.draft.domain.*;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.springframework.stereotype.Component;

@Component
class DraftProjectMapper {
    public DraftProject mapToDomain(DraftProjectJpaEntity entity) {
        Arguments1Validator<DraftProjectJpaEntity, DraftProject> draftProjectValidator = ArgumentsValidators
                .combine(
                        DraftProjectId.validator().compose(DraftProjectJpaEntity::getId),
                        ProjectOwnerId.validator().compose(DraftProjectJpaEntity::getProjectOwnerId),
                        DraftProjectName.validator().compose(DraftProjectJpaEntity::getName),
                        DraftProjectDescription.validator().compose(DraftProjectJpaEntity::getDescription),
                        RecruitmentPeriod.validator().<DraftProjectJpaEntity>compose(c -> Arguments.of(c.getRecruitmentBeginOn(), c.getRecruitmentEndOn())))
                .apply(DraftProject::of);

        return draftProjectValidator.validated(entity);
    }

    public DraftProjectJpaEntity mapToEntity(DraftProject draftProject) {
        DraftProjectJpaEntity entity = new DraftProjectJpaEntity();
        entity.setId(draftProject.getId().getValue());
        entity.setProjectOwnerId(draftProject.getProjectOwnerId().getValue());
        entity.setName(draftProject.getName().getValue());
        entity.setDescription(draftProject.getDescription().getValue());
        draftProject.getRecruitmentPeriod()
                .getBeginDate()
                .ifPresent(entity::setRecruitmentBeginOn);
        draftProject.getRecruitmentPeriod()
                .getEndDate()
                .ifPresent(entity::setRecruitmentEndOn);

        return entity;
    }
}
