package net.unit8.examples.draft.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DraftProjectId implements Serializable {
    String value;

    private static final StringValidator<DraftProjectId> validator = StringValidatorBuilder
            .of("draftProjectId", c -> c.notBlank())
            .build()
            .andThen(DraftProjectId::new);

    public static StringValidator<DraftProjectId> validator() {
        return validator;
    }

    public static DraftProjectId of(String value) {
        return validator.validate(value).orElseThrow(violations -> new IllegalArgumentException(violations.toString()));
    }
}
