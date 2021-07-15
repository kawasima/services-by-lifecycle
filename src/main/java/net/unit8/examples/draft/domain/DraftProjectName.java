package net.unit8.examples.draft.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DraftProjectName {
    String value;

    private static final StringValidator<DraftProjectName> validator = StringValidatorBuilder
            .of("draftProjectName", c -> c.notBlank().lessThanOrEqual(255))
            .build()
            .andThen(DraftProjectName::new);

    public static StringValidator<DraftProjectName> validator() {
        return validator;
    }

    public static DraftProjectName of(String value) {
        return validator.validated(value);
    }
}
