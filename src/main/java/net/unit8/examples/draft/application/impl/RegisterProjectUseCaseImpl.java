package net.unit8.examples.draft.application.impl;

import am.ik.yavi.arguments.Arguments;
import am.ik.yavi.arguments.Arguments1Validator;
import am.ik.yavi.arguments.ArgumentsValidators;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.application.RegisterProjectUseCase;
import net.unit8.examples.draft.application.SaveDraftProjectPort;
import net.unit8.examples.draft.domain.*;
import net.unit8.examples.stereotype.UseCase;
import net.unit8.examples.user.domain.ProjectOwnerId;

@UseCase
class RegisterProjectUseCaseImpl implements RegisterProjectUseCase {
    private final SaveDraftProjectPort saveDraftProjectPort;

    RegisterProjectUseCaseImpl(SaveDraftProjectPort saveDraftProjectPort) {
        this.saveDraftProjectPort = saveDraftProjectPort;
    }

    @Override
    public DraftProject handle(RegisterProjectCommand command) {
        Arguments1Validator<RegisterProjectCommand, DraftProject> draftProjectValidator = ArgumentsValidators
                .combine(
                        ProjectOwnerId.validator().compose(RegisterProjectCommand::getProjectOwnerId),
                        DraftProjectName.validator().compose(RegisterProjectCommand::getName),
                        DraftProjectDescription.validator().compose(RegisterProjectCommand::getDescription),
                        RecruitmentPeriod.validator().<RegisterProjectCommand>compose(c -> Arguments.of(c.getRecruitmentBeginOn(), c.getRecruitmentEndOn())))
                .apply((projectOwnerId, name, description, recruitmentPeriod) -> new DraftProject(
                        DraftProjectId.of(NanoIdUtils.randomNanoId()),
                        projectOwnerId, name, description, recruitmentPeriod));

        DraftProject draftProject = draftProjectValidator.validate(command)
                .orElseThrow(violations -> new IllegalArgumentException(violations.toString()));
        saveDraftProjectPort.save(draftProject);
        return draftProject;
    }
}
