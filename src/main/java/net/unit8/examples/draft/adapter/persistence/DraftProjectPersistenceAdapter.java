package net.unit8.examples.draft.adapter.persistence;

import net.unit8.examples.draft.application.GetPublicationTargetProjectsPort;
import net.unit8.examples.draft.application.SaveDraftProjectPort;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.draft.domain.DraftProjectId;
import net.unit8.examples.draft.domain.DraftProjectName;
import net.unit8.examples.stereotype.PersistenceAdapter;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.springframework.data.domain.Range;

import java.sql.Date;
import java.util.stream.Stream;

@PersistenceAdapter
class DraftProjectPersistenceAdapter implements SaveDraftProjectPort, GetPublicationTargetProjectsPort {
    private final DraftProjectMapper draftProjectMapper;
    private final DraftProjectRepository draftProjectRepository;

    public DraftProjectPersistenceAdapter(DraftProjectMapper draftProjectMapper, DraftProjectRepository draftProjectRepository) {
        this.draftProjectMapper = draftProjectMapper;
        this.draftProjectRepository = draftProjectRepository;
    }

    @Override
    public void save(DraftProject project) {
        DraftProjectJpaEntity entity = draftProjectMapper
                .mapToEntity(project);
        draftProjectRepository.save(entity);
    }

    @Override
    public Stream<DraftProject> getPublicationTargetProjects() {
        Date standardDate = new Date(System.currentTimeMillis());
        return draftProjectRepository.findPublicationTargetProjects(standardDate)
                .map(draftProjectMapper::mapToDomain)
                .stream();
    }
}
