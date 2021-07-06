package net.unit8.examples.proposal.selection1st.application.impl;

import net.unit8.examples.proposal.selection1st.domain.SelectProposal1stRound;
import net.unit8.examples.proposal.selection1st.application.Select1stRoundCommand;
import net.unit8.examples.proposal.selection1st.application.SaveSelect1stRoundPort;
import net.unit8.examples.proposal.selection1st.application.Select1stRoundUseCase;
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
