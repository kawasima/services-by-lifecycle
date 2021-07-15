package net.unit8.examples.proposal.domain;

import am.ik.yavi.arguments.StringValidator;
import am.ik.yavi.builder.StringValidatorBuilder;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProposalId implements Serializable {
    String value;

    private static final StringValidator<ProposalId> validator = StringValidatorBuilder
            .of("proposalId", c -> c.notBlank())
            .build()
            .andThen(ProposalId::new);

    public static StringValidator<ProposalId> validator() {
        return validator;
    }

    public ProposalId() {
        value = NanoIdUtils.randomNanoId();
    }

    public static ProposalId of(String value) {
        return validator.validated(value);
    }

}
