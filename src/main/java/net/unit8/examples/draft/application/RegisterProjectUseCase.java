package net.unit8.examples.draft.application;

import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.domain.DraftProject;

public interface RegisterProjectUseCase {
    DraftProject handle(RegisterProjectCommand command);
}
