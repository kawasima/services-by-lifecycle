package net.unit8.examples.projectsearch.application.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.unit8.examples.projectsearch.adapter.persistence.ProjectIndex;
import net.unit8.examples.projectsearch.adapter.persistence.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PublishProjectMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(PublishProjectMessageListener.class);
    private final ProjectRepository projectRepository;
    private final ObjectMapper objectMapper;

    public PublishProjectMessageListener(ProjectRepository projectRepository, ObjectMapper objectMapper) {
        this.projectRepository = projectRepository;
        this.objectMapper = objectMapper;
    }

    @JmsListener(containerFactory = "myJmsListenerContainerFactory", destination = "project", concurrency = "1")
    @Override
    public void onMessage(Message message) {
        try {
            Map<String, Object> event = objectMapper.readValue(((TextMessage) message).getText(), new TypeReference<>() {
            });
            LOG.info("Receive = {}", event);
            List<Map<String, Object>> projects = (List<Map<String, Object>>) event.get("projects");
            projectRepository.saveAll(projects.stream().map(
                    p -> ProjectIndex.builder()
                            .name(Objects.toString(p.get("name")))
                            .description(Objects.toString(p.get("description")))
                            .build()
            ).collect(Collectors.toUnmodifiableList()));
        } catch (JMSException | JsonProcessingException e) {
            LOG.error("Published message", e);
        }
    }
}
