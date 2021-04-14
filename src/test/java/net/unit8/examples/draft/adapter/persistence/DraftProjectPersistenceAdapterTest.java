package net.unit8.examples.draft.adapter.persistence;

import net.unit8.examples.draft.adapter.persistence.entity.DraftProjectJpaEntity;
import net.unit8.examples.draft.adapter.persistence.mapper.DraftProjectMapper;
import net.unit8.examples.draft.adapter.persistence.repository.DraftProjectRepository;
import net.unit8.examples.draft.domain.DraftProject;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Range;

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 永続化層のテスト.
 * 実際にデータベースアクセスしないと、テストにならないので、DataJpaTestを使ってテスト用データベースを構成し、
 * SQLを実行して結果をアサーションする。
 * そのため、Springコンテナを起動しDDLを発行するので、実行はかなり遅くなる。
 *
 * @author kawasima
 */
@DataJpaTest
@Import({DraftProjectPersistenceAdapter.class, DraftProjectMapper.class})
class DraftProjectPersistenceAdapterTest {
    @Autowired
    private DraftProjectPersistenceAdapter sut;

    @Autowired
    private DraftProjectRepository draftProjectRepository;

    @Test
    void saveDraftProject() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DraftProject draftProject = DraftProject.withoutId(new ProjectOwnerId(1L),
                "project1", "description1",
                Range.closed(df.parse("12/15/2020"), df.parse("01/16/2021")));
        sut.save(draftProject);
        assertThat(draftProjectRepository.count()).isEqualTo(1);

        DraftProjectJpaEntity entity = draftProjectRepository.findAll().get(0);
        assertThat(entity.getName()).isEqualTo("project1");
    }
}