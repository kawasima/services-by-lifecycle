package net.unit8.examples.draft.adapter.persistence;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import net.unit8.examples.draft.domain.DraftProject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate beginDate = LocalDate.parse("2020/12/15", df);
        LocalDate endDate = LocalDate.parse("2021/01/16", df);
        entity.setId(NanoIdUtils.randomNanoId());
        entity.setName(name);
        entity.setDescription("description1");
        entity.setRecruitmentBeginOn(beginDate);
        entity.setRecruitmentEndOn(endDate);

        DraftProject draftProject = sut.mapToDomain(entity);
        assertThat(draftProject)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("description", description);
        assertThat(draftProject.getRecruitmentPeriod().contains(beginDate))
                .isTrue();
        assertThat(draftProject.getRecruitmentPeriod().contains(beginDate.minus(1, ChronoUnit.DAYS)))
                .isFalse();
        assertThat(draftProject.getRecruitmentPeriod().contains(endDate))
                .isTrue();
        assertThat(draftProject.getRecruitmentPeriod().contains(endDate.plus(1, ChronoUnit.DAYS)))
                .isFalse();
    }

}