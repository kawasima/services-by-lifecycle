package net.unit8.examples.proposal.selection1st.application.internal.usecase;

import net.unit8.examples.proposal.selection1st.application.SelectProposal1stRound;
import net.unit8.examples.proposal.selection1st.application.command.Select1stRoundCommand;
import net.unit8.examples.proposal.selection1st.application.port.SaveSelect1stRoundPort;
import net.unit8.examples.proposal.selection1st.application.usecase.Select1stRoundUseCase;
import net.unit8.examples.stereotype.UseCase;

@UseCase
public class Select1stRoundUseCaseImpl implements Select1stRoundUseCase {
    private final SaveSelect1stRoundPort saveSelect1stRoundPort;

    public Select1stRoundUseCaseImpl(SaveSelect1stRoundPort saveSelect1stRoundPort) {
        this.saveSelect1stRoundPort = saveSelect1stRoundPort;
    }

    @Override
    public void handle(Select1stRoundCommand command) {
        saveSelect1stRoundPort.save(SelectProposal1stRound.withoutId(command.getReason()));
    }
}
