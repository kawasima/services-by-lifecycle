package net.unit8.examples.draft.application.event;

import lombok.Value;
import net.unit8.examples.draft.domain.DraftProject;
import org.zeromq.jms.ZmqObjectMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PublishProjectEvent implements Serializable {
    ArrayList<PublishedProject> projects;

    public PublishProjectEvent(List<DraftProject> draftProjects) {
        projects = draftProjects.stream()
                .map(dp -> new PublishedProject(dp.getId().getValue(), dp.getName(), dp.getDescription()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Message asMessage() {
        ZmqObjectMessage message = new ZmqObjectMessage();
        try {
            message.setObject(projects);
            return message;
        } catch (JMSException e) {
            throw new IllegalArgumentException("PublishProjectEvent is wrong", e);
        }
    }

    @Value
    public static class PublishedProject implements Serializable {
        Long id;
        String name;
        String description;
    }
}
