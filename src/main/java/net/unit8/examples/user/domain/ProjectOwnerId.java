package net.unit8.examples.user.domain;

import lombok.Value;

import java.io.Serializable;

@Value
public class ProjectOwnerId implements Serializable {
    Long value;
}
