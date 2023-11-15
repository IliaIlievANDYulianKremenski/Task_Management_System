package com.iliailievyuliankremenskiood.taskmanagement.commands.actions;

import com.iliailievyuliankremenskiood.taskmanagement.commands.contracts.Command;
import com.iliailievyuliankremenskiood.taskmanagement.core.contracts.TeamManagementRepository;
import com.iliailievyuliankremenskiood.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.taskmanagement.utils.ValidationHelpers;

import java.util.List;

public class CreatePersonCommand implements Command {
    /**
     * Command format: Create_New_Person {person name}
     * */
    /*<-------Constant(s)------->*/
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;


    /*<-------Field(s)------->*/
    private final TeamManagementRepository teamManagementRepository;


    /*<-------Constructor(s)------->*/
    public CreatePersonCommand(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }


    /*<-------Behavioural Method(s)------->*/
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String personName = parameters.get(0);
        Member temporaryMember = teamManagementRepository.createMember(personName);

        return userOutput(temporaryMember);
    }

    private static String userOutput(Member memberWhoseActivityLogWeNeed) {
        return memberWhoseActivityLogWeNeed
                .getActivityHistory()
                .get(memberWhoseActivityLogWeNeed.getActivityHistory().size() - 1);
    }
}
