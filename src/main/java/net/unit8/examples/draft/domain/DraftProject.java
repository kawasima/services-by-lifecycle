package net.unit8.examples.draft.domain;

import am.ik.yavi.arguments.Arguments5Validator;
import am.ik.yavi.arguments.ArgumentsValidators;
import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ApplicativeValidator;
import am.ik.yavi.core.ConstraintGroup;
import am.ik.yavi.core.Validated;
import am.ik.yavi.core.Validator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import net.unit8.examples.user.domain.ProjectOwnerId;

import java.io.Serializable;
import java.util.Locale;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DraftProject implements Serializable {
    private static Validator<DraftProject> validator = ValidatorBuilder.of(DraftProject.class)
            .constraintOnObject(DraftProject::getId, "id", c -> c.notNull())
            .constraintOnObject(DraftProject::getProjectOwnerId, "projectOwnerId", c -> c.notNull())
            .constraintOnObject(DraftProject::getName, "name", c -> c.notNull())
            .constraintOnObject(DraftProject::getDescription, "description", c -> c.notNull())
            .constraintOnObject(DraftProject::getRecruitmentPeriod, "recruitmentPeriod", c -> c.notNull())
            .build();

    DraftProjectId id;
    ProjectOwnerId projectOwnerId;
    DraftProjectName name;
    DraftProjectDescription description;
    RecruitmentPeriod recruitmentPeriod;

    public static Arguments5Validator<DraftProjectId, ProjectOwnerId, DraftProjectName, DraftProjectDescription, RecruitmentPeriod, DraftProject> validator() {
        return (draftProjectId, projectOwnerId, draftProjectName, draftProjectDescription, recruitmentPeriod, locale, constraintGroup) -> validator
                .applicative()
                .validate(new DraftProject(draftProjectId, projectOwnerId, draftProjectName, draftProjectDescription, recruitmentPeriod), locale, constraintGroup);
    }

    public static DraftProject of(DraftProjectId id,
                     ProjectOwnerId projectOwnerId,
                     DraftProjectName name,
                     DraftProjectDescription description,
                     RecruitmentPeriod recruitmentPeriod) {
        return validator.applicative().validated(new DraftProject(id, projectOwnerId, name, description, recruitmentPeriod));
    }
}