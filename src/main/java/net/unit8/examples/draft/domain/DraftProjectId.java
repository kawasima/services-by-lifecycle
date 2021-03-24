package net.unit8.examples.draft.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@RequiredArgsConstructor
public class DraftProjectId implements Serializable {
    Long value;
}
