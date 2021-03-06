package net.unit8.examples.draft.application.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.application.SaveDraftProjectPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    TransactionTemplate tx;

    @BeforeEach
    void setup() {
        saveDraftProjectPort = Mockito.mock(SaveDraftProjectPort.class);
        PlatformTransactionManager tm = Mockito.mock(PlatformTransactionManager.class);
        tx = new TransactionTemplate(tm);
        sut = new RegisterProjectUseCaseImpl(saveDraftProjectPort, tx);
    }

    @Test
    void test() throws ParseException {
        String name = "project1";
        String description = "description1";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate beginDate = LocalDate.parse("2020/12/15", df);
        LocalDate endDate = LocalDate.parse("2021/01/16", df);

        RegisterProjectCommand registerProjectCommand = new RegisterProjectCommand(
                NanoIdUtils.randomNanoId(),
                name,
                description,
                beginDate,
                endDate
        );
        sut.handle(registerProjectCommand);
        verify(saveDraftProjectPort).save(any());
    }
}