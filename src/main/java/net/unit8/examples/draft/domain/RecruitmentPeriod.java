package net.unit8.examples.draft.domain;

import am.ik.yavi.arguments.Arguments2Validator;
import am.ik.yavi.builder.ArgumentsValidatorBuilder;
import org.springframework.data.domain.Range;

import java.time.LocalDate;
import java.util.Optional;

public class RecruitmentPeriod {
    private final Range<? super LocalDate> period;
    private RecruitmentPeriod(LocalDate beginDate, LocalDate endDate) {
        period = Range.closed(beginDate, endDate);
    }

    private static final Arguments2Validator<LocalDate, LocalDate, RecruitmentPeriod> validator = ArgumentsValidatorBuilder
            .of(RecruitmentPeriod::new)
            .builder(b -> b.constraintOnTarget(p -> p.arg1().isBefore(p.arg2()), "", "", ""))
            .build();

    public static Arguments2Validator<LocalDate, LocalDate, RecruitmentPeriod> validator() {
        return validator;
    }

    public static RecruitmentPeriod of(LocalDate beginDate, LocalDate endDate) {
        return validator.validate(beginDate, endDate).orElseThrow(violations -> new IllegalArgumentException(violations.toString()));
    }

    public Optional<LocalDate> getBeginDate() {
        return period.getLowerBound().getValue().map(LocalDate.class::cast);
    }

    public Optional<LocalDate> getEndDate() {
        return period.getUpperBound().getValue().map(LocalDate.class::cast);
    }

    public boolean contains(LocalDate date) {
        return period.contains(date);
    }
}
