package net.unit8.examples.draft.application;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Value
@RequiredArgsConstructor
public class RegisterProjectCommand implements Serializable {
    String projectOwnerId;
    String name;
    String description;
    LocalDate recruitmentBeginOn;
    LocalDate recruitmentEndOn;
}
