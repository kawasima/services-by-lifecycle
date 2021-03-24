package net.unit8.examples.draft.application.usecase;

import net.unit8.examples.draft.application.command.RegisterProjectCommand;
import net.unit8.examples.draft.domain.DraftProject;

public interface RegisterProjectUseCase {
    DraftProject handle(RegisterProjectCommand command);
}
