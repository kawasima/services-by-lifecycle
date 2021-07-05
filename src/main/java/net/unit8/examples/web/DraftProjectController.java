package net.unit8.examples.web;

import net.unit8.examples.draft.application.RegisterProjectCommand;
import net.unit8.examples.draft.application.RegisterProjectUseCase;
import net.unit8.examples.stereotype.WebAdapter;
import net.unit8.examples.user.domain.Member;
import net.unit8.examples.user.domain.Requester;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.ParseException;

@WebAdapter
@Controller
@SessionAttributes(types = ProjectRegistrationForm.class)
@RequestMapping("/project")
public class DraftProjectController {
    private final RegisterProjectUseCase registerProjectUseCase;

    public DraftProjectController(RegisterProjectUseCase registerProjectUseCase) {
        this.registerProjectUseCase = registerProjectUseCase;
    }

    @ModelAttribute("projectRegistrationForm")
    public ProjectRegistrationForm setupForm() {
        return new ProjectRegistrationForm();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(Model model) {
        return "project/newForm";
    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String confirmForm(@Validated ProjectRegistrationForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "project/newForm";
        }
        return "project/confirm";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String save(@ModelAttribute("projectRegistrationForm") @Validated ProjectRegistrationForm form,
                       @AuthenticationPrincipal Member member,
                       SessionStatus sessionStatus) throws ParseException {
        if (member instanceof Requester requester) {
            registerProjectUseCase.handle(new RegisterProjectCommand(
                    requester.getProjectOwnerId().getValue(),
                    form.getName(),
                    form.getDescription(),
                    form.getRecruitmentBeginOn(),
                    form.getRecruitmentEndOn()
            ));
            sessionStatus.setComplete();
            return "project/complete";
        } else {
            throw new IllegalArgumentException("You are not a requester");
        }
    }

}
