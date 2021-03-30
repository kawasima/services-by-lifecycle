package net.unit8.examples.draft.adapter.persistence.mapper;

import net.unit8.examples.draft.adapter.persistence.entity.DraftProjectJpaEntity;
import net.unit8.examples.draft.domain.DraftProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ドメイン層と永続化層のデータ変換のテスト.
 *
 * これは依存したとしても他のMapperクラスだけなので、Springの機能を使わずにテストが書ける。
 *
 * @author kawasima
 */
class DraftProjectMapperTest {
    private DraftProjectMapper sut;

    @BeforeEach
    void setup() {
        sut = new DraftProjectMapper();
    }

    @Test
    void mapper() throws ParseException {
        DraftProjectJpaEntity entity = new DraftProjectJpaEntity();
        String name = "project1";
        String description = "description1";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date beginDate = df.parse("2020/12/15");
        Date endDate = df.parse("2021/1/16");
        entity.setName(name);
        entity.setDescription("description1");
        entity.setRecruitmentBeginOn(new java.sql.Date(beginDate.getTime()));
        entity.setRecruitmentEndOn(new java.sql.Date(endDate.getTime()));

        DraftProject draftProject = sut.mapToDomain(entity);
        assertThat(draftProject)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("description", description);
        assertThat(draftProject.getRecruitmentPeriod().contains(beginDate))
                .isTrue();
        assertThat(draftProject.getRecruitmentPeriod().contains(new Date(beginDate.getTime() - 1)))
                .isFalse();
        assertThat(draftProject.getRecruitmentPeriod().contains(endDate))
                .isTrue();
        assertThat(draftProject.getRecruitmentPeriod().contains(new Date(endDate.getTime() + 1)))
                .isFalse();
    }

}