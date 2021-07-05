package net.unit8.examples.draft.application;

import net.unit8.examples.draft.domain.DraftProject;

import java.util.stream.Stream;

public interface GetPublicationTargetProjectsPort {
    Stream<DraftProject> getPublicationTargetProjects();
}
