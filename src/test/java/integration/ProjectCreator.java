package integration;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectCreator {
    private void registerProject(String name, String description) {
        open("/project/new");
        $("#project-name").setValue(name);
        $("#project-description").setValue(description);
        $("#project-recruitment-period").parent()
                .find(".datetimepicker-dummy-input")
                .click();
        $$(".datepicker-days .datepicker-date.is-current-month")
                .first()
                .click();
        $$(".datepicker-days .datepicker-date.is-current-month")
                .last()
                .click();
        $("#button-registration").click();
        $("#project-name").shouldBe(exactText(name));
        $("#project-description").shouldBe(exactText(description));
        $("#button-registration").click();
        assertThat(WebDriverRunner.url()).endsWith("/project/save");
    }

    @Test
    void projectSetup() {
        registerProject("Project1", "Description for Project1");
        registerProject("Project2", "Description for Project2");
        registerProject("Project3", "Description for Project3");
    }

}
