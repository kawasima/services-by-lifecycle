package net.unit8.examples.draft.application.command;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.unit8.examples.user.domain.ProjectOwnerId;

import java.io.Serializable;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class RegisterProjectCommand implements Serializable {
    ProjectOwnerId projectOwnerId;
    String name;
    String description;
    Date recruitmentBeginOn;
    Date recruitmentEndOn;
}
