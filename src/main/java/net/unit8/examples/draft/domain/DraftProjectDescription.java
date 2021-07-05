package net.unit8.examples.draft.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import lombok.Value;

@Value
public class DraftProjectDescription {
    String value;

    private static final StringValidator<DraftProjectDescription> validator = StringValidatorBuilder
            .of("draftProjectDescription", c -> c.notBlank())
            .build()
            .andThen(DraftProjectDescription::new);

    public static StringValidator<DraftProjectDescription> validator() {
        return validator;
    }

    public static DraftProjectDescription of(String value) {
        return validator.validate(value).orElseThrow(ConstraintViolationsException::new);
    }
}
