package net.unit8.examples.proposal.submission.adapter.persistence.mapper;

import net.unit8.examples.proposal.submission.adapter.persistence.entity.FixedEstimationJpaEntity;
import net.unit8.examples.proposal.submission.adapter.persistence.entity.PerHourEstimationJpaEntity;
import net.unit8.examples.proposal.submission.domain.FixedEstimation;
import net.unit8.examples.proposal.submission.domain.PerHourEstimation;
import org.springframework.stereotype.Component;

@Component
public class EstimationMapper {
    public FixedEstimationJpaEntity mapToEntity(FixedEstimation domain) {
        FixedEstimationJpaEntity entity = new FixedEstimationJpaEntity();
        entity.setTotal(domain.getTotal());
        return entity;
    }

    public FixedEstimation mapToDomain(FixedEstimationJpaEntity entity) {
        return new FixedEstimation(entity.getTotal());
    }

    public PerHourEstimationJpaEntity mapToEntity(PerHourEstimation domain) {
        PerHourEstimationJpaEntity entity = new PerHourEstimationJpaEntity();
        entity.setHours(domain.getHours());
        entity.setUnitPrice(domain.getUnitPrice());
        return entity;
    }

    public PerHourEstimation mapToDomain(PerHourEstimationJpaEntity entity) {
        return new PerHourEstimation(entity.getUnitPrice(), entity.getHours());
    }

}
