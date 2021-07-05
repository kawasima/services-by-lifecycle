package net.unit8.examples.draft.domain;

import lombok.Value;
import net.unit8.examples.user.domain.ProjectOwnerId;

import java.io.Serializable;

@Value
public class DraftProject implements Serializable {
    DraftProjectId id;
    ProjectOwnerId projectOwnerId;
    DraftProjectName name;
    DraftProjectDescription description;
    RecruitmentPeriod recruitmentPeriod;

    public static DraftProject of(DraftProjectId id,
                     ProjectOwnerId projectOwnerId,
                     DraftProjectName name,
                     DraftProjectDescription description,
                     RecruitmentPeriod recruitmentPeriod) {
        return new DraftProject(id, projectOwnerId, name, description, recruitmentPeriod);
    }
}