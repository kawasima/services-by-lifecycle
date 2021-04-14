package net.unit8.examples.draft.adapter.persistence;

import net.unit8.examples.draft.adapter.persistence.entity.DraftProjectJpaEntity;
import net.unit8.examples.draft.adapter.persistence.mapper.DraftProjectMapper;
import net.unit8.examples.draft.adapter.persistence.repository.DraftProjectRepository;
import net.unit8.examples.draft.application.port.GetPublicationTargetProjectsPort;
import net.unit8.examples.draft.application.port.SaveDraftProjectPort;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.draft.domain.DraftProjectId;
import net.unit8.examples.stereotype.PersistenceAdapter;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.springframework.data.domain.Range;

import java.sql.Date;
import java.util.stream.Stream;

@PersistenceAdapter
public class DraftProjectPersistenceAdapter implements SaveDraftProjectPort, GetPublicationTargetProjectsPort {
    private final DraftProjectMapper draftProjectMapper;
    private final DraftProjectRepository draftProjectRepository;

    public DraftProjectPersistenceAdapter(DraftProjectMapper draftProjectMapper, DraftProjectRepository draftProjectRepository) {
        this.draftProjectMapper = draftProjectMapper;
        this.draftProjectRepository = draftProjectRepository;
    }

    @Override
    public DraftProject save(DraftProject project) {
        DraftProjectJpaEntity entity = draftProjectMapper
                .mapToEntity(project);
        draftProjectRepository.save(entity);
        return DraftProject.withId(
                new DraftProjectId(entity.getId()),
                new ProjectOwnerId(entity.getProjectOwnerId()),
                entity.getName(),
                entity.getDescription(),
                Range.closed(entity.getRecruitmentBeginOn(), entity.getRecruitmentEndOn())
        );
    }

    @Override
    public Stream<DraftProject> getPublicationTargetProjects() {
        Date standardDate = new Date(System.currentTimeMillis());
        return draftProjectRepository.findPublicationTargetProjects(standardDate)
                .map(draftProjectMapper::mapToDomain)
                .stream();
    }
}
