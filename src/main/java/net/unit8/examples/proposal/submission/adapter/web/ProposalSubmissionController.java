package net.unit8.examples.proposal.submission.adapter.web;

import net.unit8.examples.projectsearch.application.command.SearchProjectCommand;
import net.unit8.examples.projectsearch.application.domain.Project;
import net.unit8.examples.projectsearch.application.domain.ProjectId;
import net.unit8.examples.projectsearch.application.usecase.SearchProjectUseCase;
import net.unit8.examples.proposal.submission.application.command.SubmitProposalCommand;
import net.unit8.examples.proposal.submission.application.usecase.SubmitProposalUseCase;
import net.unit8.examples.proposal.submission.domain.FixedEstimation;
import net.unit8.examples.proposal.submission.domain.PerHourEstimation;
import net.unit8.examples.stereotype.WebAdapter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebAdapter
@Controller
@SessionAttributes(types = ProposalSubmissionForm.class)
@RequestMapping("/proposal/submission")
public class ProposalSubmissionController {
    private final SearchProjectUseCase searchProjectUseCase;
    private final SubmitProposalUseCase submitProposalUseCase;

    public ProposalSubmissionController(SearchProjectUseCase searchProjectUseCase, SubmitProposalUseCase submitProposalUseCase) {
        this.searchProjectUseCase = searchProjectUseCase;
        this.submitProposalUseCase = submitProposalUseCase;
    }

    @ModelAttribute("proposalSubmissionForm")
    public ProposalSubmissionForm setupForm() {
        return new ProposalSubmissionForm();
    }

    @RequestMapping("/searchProject")
    public String searchProject(@RequestParam(name = "q", required = false) String query,
                                @RequestParam(name = "p", required = false) Long pageNumber,
                                Model model) {
        SearchProjectCommand command = new SearchProjectCommand(query);
        Page<Project> projects = searchProjectUseCase.search(command);
        model.addAttribute("projects", projects);
        int totalPages = projects.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "proposal_submission/searchProject";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newForm(Model model, @RequestParam("projectId") String projectId) {
        ProposalSubmissionForm form = (ProposalSubmissionForm) model.getAttribute("proposalSubmissionForm");
        form.setProjectId(projectId);
        return "proposal_submission/newForm";
    }

    String confirmForm(Model model, ProposalSubmissionForm form, BindingResult result) {
        if (result.hasErrors()){
            return newForm(model, form.getProjectId());
        }
        return "proposal_submission/confirm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = {"estimationType=fixed"})
    public String confirmFormForFixedEstimation(Model model,
                                                @Validated({FixedEstimationGroup.class}) ProposalSubmissionForm form,
                                                BindingResult result) {
        return confirmForm(model, form, result);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, params = {"estimationType=perHour"})
    public String confirmFormForPerHourEstimation(Model model,
                                                @Validated({PerHourEstimationGroup.class}) ProposalSubmissionForm form,
                                                BindingResult result) {
        return confirmForm(model, form, result);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("proposalSubmissionForm") @Validated ProposalSubmissionForm form,
                       Model model,
                       SessionStatus sessionStatus) {
        submitProposalUseCase.handle(new SubmitProposalCommand(
                new ProjectId(form.getProjectId()),
                new FixedEstimation(form.getTotal())
        ));
        sessionStatus.setComplete();
        return "proposal_submission/complete";
    }

}
