package net.unit8.examples.draft.application.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class RegisterProjectCommand implements Serializable {
    String name;
    String description;
    Date recruitmentBeginOn;
    Date recruitmentEndOn;
}
