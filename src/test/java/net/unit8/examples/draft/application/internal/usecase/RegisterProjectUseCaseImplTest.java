package net.unit8.examples.draft.application.internal.usecase;

import net.unit8.examples.draft.application.command.RegisterProjectCommand;
import net.unit8.examples.draft.application.port.SaveDraftProjectPort;
import net.unit8.examples.user.domain.ProjectOwnerId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * プロジェクト登録のユースケースのテスト.
 * Springのテストサポート機能を使わずとも、ユースケースが依存する下位レイヤのコンポーネントをモック化すれば、
 * 簡単にテストできる。
 *
 * 入力はコマンドとして作り、ユースケースを呼び出し、下位レイヤが正しく呼ばれたかをMockitoのVerifyで検証し、
 * (必要ならば) 出力もAssertionする。
 *
 * @author kawasima
 */
class RegisterProjectUseCaseImplTest {
    SaveDraftProjectPort saveDraftProjectPort;
    RegisterProjectUseCaseImpl sut;
    @BeforeEach
    void setup() {
        saveDraftProjectPort = Mockito.mock(SaveDraftProjectPort.class);
        sut = new RegisterProjectUseCaseImpl(saveDraftProjectPort);
    }

    @Test
    void test() throws ParseException {
        String name = "project1";
        String description = "description1";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date beginDate = df.parse("2020/12/15");
        Date endDate = df.parse("2021/1/16");

        RegisterProjectCommand registerProjectCommand = new RegisterProjectCommand(
                new ProjectOwnerId(1L),
                name,
                description,
                beginDate,
                endDate
        );
        sut.handle(registerProjectCommand);
        verify(saveDraftProjectPort).save(any());
    }
}