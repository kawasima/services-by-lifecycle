package net.unit8.examples.draft.adapter.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.unit8.examples.draft.domain.PublishProjectEvent;
import net.unit8.examples.draft.application.PublishProjectPort;
import net.unit8.examples.stereotype.PersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

@PersistenceAdapter
public class ProjectMessagingAdapter implements PublishProjectPort {
    public static final Logger LOG = LoggerFactory.getLogger(ProjectMessagingAdapter.class);
    private final JmsTemplate jmsTemplate;
    private final PublishedProjectRepository publishedProjectRepository;
    private final DraftProjectRepository draftProjectRepository;
    private final ObjectMapper objectMapper;

    public ProjectMessagingAdapter(JmsTemplate jmsTemplate, PublishedProjectRepository publishedProjectRepository, DraftProjectRepository draftProjectRepository, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.publishedProjectRepository = publishedProjectRepository;
        this.draftProjectRepository = draftProjectRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(PublishProjectEvent event) {
        LOG.info("send message: {}", event);
        jmsTemplate.send(session -> {
            try {
                return session.createTextMessage(
                        objectMapper.writeValueAsString(event)
                );
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(event.toString(), e);
            }
        });
        event.getProjects().forEach(project -> {
            PublishedProjectJpaEntity entity = new PublishedProjectJpaEntity();
            draftProjectRepository.findById(project.getId()).ifPresent(p -> {
                entity.setDraftProject(p);
                publishedProjectRepository.save(entity);
            });
        });
    }
}
