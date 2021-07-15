package net.unit8.examples.projectsearch.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectDescription {
    String value;

    private static final StringValidator<ProjectDescription> validator = StringValidatorBuilder
            .of("projectDescription", c -> c.notBlank().lessThanOrEqual(255))
            .build()
            .andThen(ProjectDescription::new);

    public static StringValidator<ProjectDescription> validator() {
        return validator;
    }

    public static ProjectDescription of(String value) {
        return validator.validated(value);
    }
}
