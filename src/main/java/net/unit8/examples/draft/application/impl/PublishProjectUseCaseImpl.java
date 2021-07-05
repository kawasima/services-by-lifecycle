package net.unit8.examples.draft.application.impl;

import net.unit8.examples.draft.domain.PublishProjectEvent;
import net.unit8.examples.draft.application.GetPublicationTargetProjectsPort;
import net.unit8.examples.draft.application.PublishProjectPort;
import net.unit8.examples.draft.application.PublishProjectUseCase;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.stereotype.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

@UseCase
public class PublishProjectUseCaseImpl implements PublishProjectUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(PublishProjectUseCaseImpl.class);

    private final GetPublicationTargetProjectsPort getPublicationTargetProjectsPort;
    private final PublishProjectPort publishProjectPort;

    public PublishProjectUseCaseImpl(GetPublicationTargetProjectsPort getPublicationTargetProjectsPort, PublishProjectPort publishProjectPort) {
        this.getPublicationTargetProjectsPort = getPublicationTargetProjectsPort;
        this.publishProjectPort = publishProjectPort;
    }

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Tokyo")
    @Override
    public void handle() {
        LOG.info("Start Publish Task");
        BatchSpliterator<DraftProject> draftProjectSpliterator = new BatchSpliterator<>(getPublicationTargetProjectsPort.getPublicationTargetProjects()
                .spliterator(), 100);

        StreamSupport.stream(draftProjectSpliterator, false)
                .map(PublishProjectEvent::new)
                .forEach(publishProjectPort::publish);
    }

    private static class BatchSpliterator<E> implements Spliterator<List<E>> {
        private final Spliterator<E> base;
        private final int batchSize;

        public BatchSpliterator(Spliterator<E> base, int batchSize) {
            this.base = base;
            this.batchSize = batchSize;
        }

        @Override
        public boolean tryAdvance(Consumer<? super List<E>> action) {
            final List<E> batch = new ArrayList<>(batchSize);
            for (int i = 0; i < batchSize && base.tryAdvance(batch::add); i++)
                ;
            if (batch.isEmpty())
                return false;
            action.accept(batch);
            return true;
        }

        @Override
        public Spliterator<List<E>> trySplit() {
            if (base.estimateSize() <= batchSize)
                return null;
            final Spliterator<E> splitBase = this.base.trySplit();
            return splitBase == null ? null
                    : new BatchSpliterator<>(splitBase, batchSize);
        }

        @Override
        public long estimateSize() {
            final double baseSize = base.estimateSize();
            return baseSize == 0 ? 0
                    : (long) Math.ceil(baseSize / (double) batchSize);
        }

        @Override
        public int characteristics() {
            return base.characteristics();
        }
    }
}