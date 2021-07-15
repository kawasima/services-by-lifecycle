package net.unit8.examples.projectsearch.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectName {
    String value;

    private static final StringValidator<ProjectName> validator = StringValidatorBuilder
            .of("projectName", c -> c.notBlank().lessThanOrEqual(255))
            .build()
            .andThen(ProjectName::new);

    public static StringValidator<ProjectName> validator() {
        return validator;
    }

    public static ProjectName of(String value) {
        return validator.validated(value);
    }

}
