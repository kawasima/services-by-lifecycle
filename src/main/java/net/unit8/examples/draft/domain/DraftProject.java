package net.unit8.examples.draft.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Range;

import java.io.Serializable;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class DraftProject implements Serializable {
    DraftProjectId id;
    String name;
    String description;
    Range<Date> recruitmentPeriod;

    public static DraftProject withId(
            DraftProjectId id,
            String name,
            String description,
            Range<Date> recruitmentPeriod
    ) {
        return new DraftProject(id, name, description, recruitmentPeriod);
    }

    public static DraftProject withoutId(
            String name,
            String description,
            Range<Date> recruitmentPeriod
    ) {
        return new DraftProject(null, name, description, recruitmentPeriod);
    }

}