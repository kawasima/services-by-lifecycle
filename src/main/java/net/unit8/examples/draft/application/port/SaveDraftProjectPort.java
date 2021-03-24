package net.unit8.examples.draft.application.port;

import net.unit8.examples.draft.domain.DraftProject;

public interface SaveDraftProjectPort {
    DraftProject save(DraftProject project);
}
