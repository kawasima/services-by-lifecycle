package net.unit8.examples.draft.application;

import net.unit8.examples.draft.domain.PublishProjectEvent;

public interface PublishProjectPort {
    void publish(PublishProjectEvent event);
}
