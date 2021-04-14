package net.unit8.examples.draft.application.internal.usecase;

import net.unit8.examples.draft.application.command.RegisterProjectCommand;
import net.unit8.examples.draft.application.port.SaveDraftProjectPort;
import net.unit8.examples.draft.application.usecase.RegisterProjectUseCase;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.stereotype.UseCase;
import org.springframework.data.domain.Range;

@UseCase
class RegisterProjectUseCaseImpl implements RegisterProjectUseCase {
    private final SaveDraftProjectPort saveDraftProjectPort;

    RegisterProjectUseCaseImpl(SaveDraftProjectPort saveDraftProjectPort) {
        this.saveDraftProjectPort = saveDraftProjectPort;
    }

    @Override
    public DraftProject handle(RegisterProjectCommand command) {
        return saveDraftProjectPort.save(DraftProject.withoutId(
                command.getProjectOwnerId(),
                command.getName(),
                command.getDescription(),
                Range.closed(
                        command.getRecruitmentBeginOn(),
                        command.getRecruitmentEndOn())
        ));
    }
}
