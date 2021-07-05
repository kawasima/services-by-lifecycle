package net.unit8.examples.user.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import lombok.Value;

import java.io.Serializable;

@Value
public class ProjectOwnerId implements Serializable {
    String value;

    private static final StringValidator<ProjectOwnerId> validator = StringValidatorBuilder
            .of("draftProjectName", c -> c.notBlank())
            .build()
            .andThen(ProjectOwnerId::new);

    public static StringValidator<ProjectOwnerId> validator() {
        return validator;
    }

    public static ProjectOwnerId of(String value) {
        return validator.validate(value).orElseThrow(violations -> new IllegalArgumentException(violations.toString()));
    }
}
