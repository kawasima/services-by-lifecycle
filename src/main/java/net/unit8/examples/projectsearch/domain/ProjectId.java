package net.unit8.examples.projectsearch.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import am.ik.yavi.core.ConstraintViolationsException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectId {
    String value;

    private static final StringValidator<ProjectId> validator = StringValidatorBuilder
            .of("projectId", c -> c.notBlank())
            .build()
            .andThen(ProjectId::new);

    public static StringValidator<ProjectId> validator() {
        return validator;
    }

    public static ProjectId of(String value) {
        return validator.validate(value).orElseThrow(ConstraintViolationsException::new);
    }
}
