package net.unit8.examples.draft.adapter.web;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.application.RegisterProjectUseCase;
import net.unit8.examples.web.DraftProjectController;
import net.unit8.examples.web.ProjectRegistrationForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MVCコントローラのテスト.
 * テスト目的としては、主に以下２つ
 *
 * - コントローラへの入力バリデーションを検証する
 * - ユースケースを実行した結果、呼び出されるビューが適切であるかを検証する
 *
 * このため、下位レイヤのユースケースをモック化する。
 *
 * @author kawasima
 */
@WebMvcTest(controllers = DraftProjectController.class)
class DraftProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterProjectUseCase registerProjectUseCase;

    @Test
    void testNewPost() throws Exception {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "project1");
        params.add("description", "description1");
        params.add("recruitmentPeriod", "12/15/2020 - 01/16/2020");
        mockMvc.perform(post("/project/new")
                .params(params)
        ).andExpect(status().isOk())
        .andExpect(request().sessionAttribute("projectRegistrationForm", is(notNullValue())));
    }

    @Test
    void testRegisterProject() throws Exception {
        ProjectRegistrationForm form = new ProjectRegistrationForm();
        form.setName("project1");
        form.setDescription("description1");
        form.setRecruitmentPeriod("12/15/2020 - 01/16/2021");
        mockMvc.perform(post("/project/save")
                .flashAttr("projectRegistrationForm", form))
                .andExpect(status().isOk());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        then(registerProjectUseCase).should()
                .handle(eq(new RegisterProjectCommand(
                        NanoIdUtils.randomNanoId(),
                        "project1",
                        "description1",
                        LocalDate.parse("12/15/2020", df),
                        LocalDate.parse("01/16/2021", df)
                )));
    }
}