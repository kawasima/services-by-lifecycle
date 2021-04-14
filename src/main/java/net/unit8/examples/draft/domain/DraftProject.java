package net.unit8.examples.draft.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.springframework.data.domain.Range;

import java.io.Serializable;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class DraftProject implements Serializable {
    DraftProjectId id;
    ProjectOwnerId projectOwnerId;
    String name;
    String description;
    Range<Date> recruitmentPeriod;

    public static DraftProject withId(
            DraftProjectId id,
            ProjectOwnerId projectOwnerId,
            String name,
            String description,
            Range<Date> recruitmentPeriod
    ) {
        return new DraftProject(id, projectOwnerId, name, description, recruitmentPeriod);
    }

    public static DraftProject withoutId(
            ProjectOwnerId projectOwnerId,
            String name,
            String description,
            Range<Date> recruitmentPeriod
    ) {
        return new DraftProject(null, projectOwnerId, name, description, recruitmentPeriod);
    }

}