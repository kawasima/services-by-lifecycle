package net.unit8.examples.draft.application.port;

import net.unit8.examples.draft.application.event.PublishProjectEvent;

public interface PublishProjectPort {
    void publish(PublishProjectEvent event);
}
