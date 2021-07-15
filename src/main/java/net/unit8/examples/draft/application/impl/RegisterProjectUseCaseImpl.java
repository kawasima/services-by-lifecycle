package net.unit8.examples.draft.application.impl;

import am.ik.yavi.arguments.Arguments;
import am.ik.yavi.arguments.Arguments1Validator;
import am.ik.yavi.arguments.ArgumentsValidators;
import am.ik.yavi.core.Validated;
import am.ik.yavi.fn.Validation;
import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.application.RegisterProjectUseCase;
import net.unit8.examples.draft.application.SaveDraftProjectPort;
import net.unit8.examples.draft.domain.*;
import net.unit8.examples.stereotype.UseCase;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.springframework.transaction.support.TransactionTemplate;

@UseCase
class RegisterProjectUseCaseImpl implements RegisterProjectUseCase {
    private final SaveDraftProjectPort saveDraftProjectPort;
    private final TransactionTemplate tx;

    RegisterProjectUseCaseImpl(SaveDraftProjectPort saveDraftProjectPort, TransactionTemplate tx) {
        this.saveDraftProjectPort = saveDraftProjectPort;
        this.tx = tx;
    }

    @Override
    public Validated<RegisteredProjectEvent> handle(RegisterProjectCommand command) {
        DraftProjectId id = new DraftProjectId();
        Arguments1Validator<RegisterProjectCommand, Validated<DraftProject>> validator = ArgumentsValidators
                .combine(
                        ProjectOwnerId.validator().compose(RegisterProjectCommand::getProjectOwnerId),
                        DraftProjectName.validator().compose(RegisterProjectCommand::getName),
                        DraftProjectDescription.validator().compose(RegisterProjectCommand::getDescription),
                        RecruitmentPeriod.validator().<RegisterProjectCommand>compose(c -> Arguments.of(c.getRecruitmentBeginOn(), c.getRecruitmentEndOn())))
                .apply((projectOwnerId, name, description, recruitmentPeriod) -> DraftProject.validator()
                        .validate(id, projectOwnerId, name, description, recruitmentPeriod)

                );
        Validated<DraftProject> draftProjectValidated = validator.validate(command).fold(errors -> Validated.of(Validation.failure(errors)), v -> v);
        draftProjectValidated.peek(draftProject -> tx.execute(status -> {
            saveDraftProjectPort.save(draftProject);
            return null;
        }));

        return draftProjectValidated.map(draftProject -> new RegisteredProjectEvent(draftProject.getId()));
    }
}
