package net.unit8.examples.draft.application;

import am.ik.yavi.core.Validated;
import net.unit8.examples.draft.domain.RegisteredProjectEvent;

public interface RegisterProjectUseCase {
    Validated<RegisteredProjectEvent> handle(RegisterProjectCommand command);
}
