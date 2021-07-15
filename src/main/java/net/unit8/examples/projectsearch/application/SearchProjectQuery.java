package net.unit8.examples.projectsearch.application;

import lombok.Value;

import java.io.Serializable;

@Value
public class SearchProjectQuery implements Serializable {
    String query;
}
