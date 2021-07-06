package net.unit8.examples.config;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import net.unit8.examples.user.adapter.persistence.ProjectOwnerJpaEntity;
import net.unit8.examples.user.adapter.persistence.RequesterJpaEntity;
import net.unit8.examples.user.adapter.persistence.WorkerJpaEntity;
import net.unit8.examples.user.adapter.persistence.ProjectOwnerRepository;
import net.unit8.examples.user.adapter.persistence.RequesterRepository;
import net.unit8.examples.user.adapter.persistence.WorkerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class MemberInitializer implements InitializingBean {
    private final ProjectOwnerRepository projectOwnerRepository;
    private final RequesterRepository requesterRepository;
    private final WorkerRepository workerRepository;
    private final TransactionTemplate tx;
    private final PasswordEncoder passwordEncoder;

    public MemberInitializer(ProjectOwnerRepository projectOwnerRepository, RequesterRepository requesterRepository, WorkerRepository workerRepository, TransactionTemplate tx, PasswordEncoder passwordEncoder) {
        this.projectOwnerRepository = projectOwnerRepository;
        this.requesterRepository = requesterRepository;
        this.workerRepository = workerRepository;
        this.tx = tx;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RequesterJpaEntity requester1 = new RequesterJpaEntity();
        requester1.setId(NanoIdUtils.randomNanoId());
        requester1.setEmail("requester1@example.com");
        requester1.setPassword(passwordEncoder.encode("password"));

        WorkerJpaEntity worker1 = new WorkerJpaEntity();
        worker1.setId(NanoIdUtils.randomNanoId());
        worker1.setEmail("worker1@example.com");
        worker1.setPassword(passwordEncoder.encode("password"));
        worker1.setSkill("Javaチョットデキル");
        worker1.setQualification("普通運転免許");

        ProjectOwnerJpaEntity projectOwner = new ProjectOwnerJpaEntity();
        projectOwner.setId(NanoIdUtils.randomNanoId());
        requester1.setProjectOwner(projectOwner);

        tx.execute((status) -> {
            projectOwnerRepository.save(projectOwner);
            requesterRepository.save(requester1);
            workerRepository.save(worker1);
            return null;
        });
    }
}
