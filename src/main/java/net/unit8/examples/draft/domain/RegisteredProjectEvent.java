package net.unit8.examples.draft.domain;

import lombok.Value;

@Value
public class RegisteredProjectEvent {
    DraftProjectId draftProjectId;
}
