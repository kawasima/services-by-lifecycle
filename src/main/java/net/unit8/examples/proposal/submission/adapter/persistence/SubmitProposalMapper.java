package net.unit8.examples.proposal.submission.adapter.persistence;

import net.unit8.examples.projectsearch.domain.ProjectId;
import net.unit8.examples.proposal.adapter.persistence.ProposalJpaEntity;
import net.unit8.examples.proposal.adapter.persistence.ProposalStatus;
import net.unit8.examples.proposal.domain.ProposalId;
import net.unit8.examples.proposal.submission.domain.Estimation;
import net.unit8.examples.proposal.submission.domain.FixedEstimation;
import net.unit8.examples.proposal.submission.domain.PerHourEstimation;
import net.unit8.examples.proposal.submission.domain.SubmitProposal;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
class SubmitProposalMapper {
    private final EstimationMapper estimationMapper;

    public SubmitProposalMapper(EstimationMapper estimationMapper) {
        this.estimationMapper = estimationMapper;
    }

    public ProposalSubmissionJpaEntity mapToEntity(SubmitProposal domain) {
        ProposalSubmissionJpaEntity entity = new ProposalSubmissionJpaEntity();

        ProposalJpaEntity proposalEntity = new ProposalJpaEntity();
        proposalEntity.setProjectId(domain.getProjectId().getValue());
        proposalEntity.setProposalStatus(ProposalStatus.SUBMITTED);
        entity.setProposal(proposalEntity);
        Estimation estimation = domain.getEstimation();
        if (estimation instanceof FixedEstimation e) {
            entity.setEstimation(estimationMapper.mapToEntity(e));
        } else if (estimation instanceof PerHourEstimation e) {
            entity.setEstimation(estimationMapper.mapToEntity(e));
        } else {
            throw new IllegalStateException("Unknown estimation type:" + estimation.getClass());
        }
        entity.setActedAt(new Timestamp(System.currentTimeMillis()));

        return entity;
    }

    public SubmitProposal mapToDomain(ProposalSubmissionJpaEntity entity) {
        Estimation estimation;
        if (entity.getEstimation() instanceof FixedEstimationJpaEntity e) {
            estimation = estimationMapper.mapToDomain(e);
        } else if (entity.getEstimation() instanceof PerHourEstimationJpaEntity e) {
            estimation =  estimationMapper.mapToDomain(e);
        } else {
            throw new IllegalStateException("Unknown estimation type: " + entity.getEstimation().getClass());
        }

        return SubmitProposal.withId(
                new ProposalId(entity.getId()),
                new ProjectId(entity.getProposal().getProjectId()),
                estimation
        );
    }
}
