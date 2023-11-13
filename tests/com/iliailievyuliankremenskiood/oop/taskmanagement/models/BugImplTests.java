package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BugImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_BUG_TITLE = "The program freezes when the Log In button is clicked.";
    private static final String VALID_BUG_DESCRIPTION = "This needs to be fixed quickly!";
    private static final String VALID_MEMBER_NAME = "Юлиан Кременски";
    private static final String VALID_MEMBER2_NAME = "Илия Илиев";
    private static final String SHORTER_BUG_TITLE = "123";
    private static final String LONGER_BUG_TITLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nulla vel mi ut quam rutrum vulputate. Ut in turpis luctus, elementum nisi sed, facilisis enim. " +
            "Duis porta quam orci, ac hendrerit enim molestie at. Sed pellentesque blandit ipsum. " +
            "Vivamus feugiat nibh nec nisl aliquam, sed bibendum lectus pharetra. Mauris iaculis suscipit sem " +
            "id pulvinar. Aenean pharetra quam sed sagittis accumsan. Cras accumsan lacus ac est pulvinar consectetur. " +
            "Mauris eget libero eros. Pellentesque odio odio, hendrerit a magna in, dapibus tincidunt enim. " +
            "Quisque mattis purus lacus, a venenatis purus maximus ac. Suspendisse potenti. Nam placerat imperdiet " +
            "velit, ut efficitur ex accumsan id. Donec eu magna vel nunc porttitor viverra. In id vehicula. ";

    private static final String SHORTER_BUG_DESCRIPTION = "123456789";
    private static final String LONGER_BUG_DESCRIPTION =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam tortor dolor, luctus lacinia elit quis, " +
                    "lacinia posuere mauris. Mauris ut purus euismod, sodales ligula quis, pretium orci. " +
                    "Aliquam a viverra mi, at elementum dolor. Ut finibus mi augue. Orci varius natoque penatibus " +
                    "et magnis dis parturient montes, nascetur ridiculus mus. Morbi feugiat purus nec purus efficitur " +
                    "accumsan. Morbi tortor arcu, semper condimentum maximus eget, lobortis sed dui. " +
                    "Fusce placerat arcu ac eros rhoncus, sed vestibulum magna fermentum.\n" +
                    "\n" +
                    "Aenean in tellus a dui malesuada hendrerit at et augue. Suspendisse dignissim orci in est vulputate " +
                    "imperdiet. Curabitur imperdiet odio vel urna pulvinar pulvinar. Quisque ac porta leo. Ut non " +
                    "scelerisque felis, ac bibendum purus. Mauris congue mi tortor, quis gravida tellus blandit non. " +
                    "Ut auctor nisi et nisl accumsan, quis condimentum leo tincidunt. In pharetra metus at imperdiet " +
                    "ultricies. Donec odio justo, mollis gravida porta ullamcorper, vehicula a nibh. Nam vitae nisi " +
                    "eget elit suscipit euismod a eu ipsum.\n" +
                    "\n" +
                    "Phasellus nec odio vel neque suscipit varius id porttitor quam. Orci varius natoque penatibus et magnis " +
                    "dis parturient montes, nascetur ridiculus mus. Aenean ut ultricies tortor. Vestibulum metus mi, " +
                    "pellentesque a justo eu, malesuada tincidunt mi. Vestibulum posuere hendrerit lectus, non " +
                    "vestibulum orci tempor id. Integer eu nisl et enim vulputate viverra. Donec fringilla " +
                    "leo sit amet elit auctor, non tempus est porttitor. Praesent rutrum ornare leo, hendrerit " +
                    "condimentum orci dictum eu. Aliquam faucibus erat vel scelerisque laoreet. Nulla a tristique " +
                    "ipsum. Suspendisse potenti. Ut ultricies est quis ex varius dictum.\n" +
                    "\n" +
                    "Interdum et malesuada fames ac ante ipsum primis in faucibus. Aliquam faucibus metus in eleifend " +
                    "consequat. Sed placerat sit amet metus quis suscipit. Maecenas eget lorem ut lacus suscipit " +
                    "consequat at vitae odio. Maecenas ipsum orci, ullamcorper vel nisl quis, viverra vulputate metus. " +
                    "Donec nisi leo, consequat ut posuere ac, pharetra a magna. Curabitur suscipit tincidunt lacus, " +
                    "et scelerisque arcu ullamcorper consectetur. Proin finibus nunc elit, vel vehicula turpis " +
                    "mollis eu. Sed vehicula ultrices nisi in faucibus. Integer tempor sem nibh, in accumsan nibh " +
                    "posuere id. Proin vestibulum tincidunt volutpat. Nulla a pulvinar arcu, et ullamcorper enim.\n" +
                    "\n" +
                    "Cras non fringilla velit. Curabitur vitae sem diam. Etiam iaculis malesuada posuere. Nulla consectetur " +
                    "consectetur vestibulum. Maecenas sit amet molestie lectus. Fusce in leo dignissim, suscipit diam " +
                    "vitae, facilisis est. Sed in dolor eget neque viverra dapibus eget eu leo. Duis scelerisque, " +
                    "nulla ut auctor tincidunt, sem sapien dignissim neque, ut scelerisque elit arcu vel orci. " +
                    "Praesent euismod odio sit amet bibendum gravida. Quisque scelerisque tempor pretium.\n" +
                    "\n" +
                    "Suspendisse id efficitur lacus. Pellentesque pellentesque sagittis dui id imperdiet. Morbi et nisi " +
                    "fringilla arcu molestie posuere et quis nisi. Sed est elit, eleifend sed ornare vitae, " +
                    "tincidunt eu eros. Pellentesque condimentum tincidunt risus. Nunc ac leo massa. Etiam consequat " +
                    "lacus eu efficitur tempus. Aliquam erat volutpat. Aenean ac lacus lacinia, dictum tortor eu, " +
                    "tristique enim. Aenean sit amet massa mauris. In rhoncus leo libero, sed lacinia ex lobortis sit " +
                    "amet. Suspendisse ex mauris, feugiat at porta eu, congue eu lectus. Curabitur posuere tempor " +
                    "bibendum. Nulla blandit, mauris placerat ultricies porttitor, ligula dolor consequat orci, vitae " +
                    "semper odio. ";
    private static final String VALID_BUG_PRINT = String.format("""
                    --------------
                    Bug:
                        Id: %d
                    	Title: %s
                    	Description: %s
                        Priority: %s
                        Severity: %s
                        Status: %s
                        Assignee: %s
                    --------------""", 1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION, BugPriorityType.HIGH.toString(),
            BugSeverityType.CRITICAL, BugStatusType.ACTIVE.toString(), VALID_MEMBER_NAME);
    public static final String STEP_1 = "Step 1.";
    public static final String STEP_2 = "Step 2.";

    /*TODO - A valid implementation of the "Member" class is needed for the tests. @Ilia Iliev will implement it 👍*/
    /*<-------Test(s)------->*/
    @Test
    public void Constructor_Should_CreateNewBug_When_ValidArgumentsPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);

        /*Act*/
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Assess*/
        Assertions.assertNotNull(bug);
        Assertions.assertEquals(1, bug.getId());
        Assertions.assertEquals(VALID_BUG_TITLE, bug.getTitle());
        Assertions.assertEquals(VALID_BUG_DESCRIPTION, bug.getDescription());
        Assertions.assertEquals(BugPriorityType.HIGH.toString(), bug.getPriority().toString());
        Assertions.assertEquals(BugSeverityType.CRITICAL.toString(), bug.getSeverity().toString());
        Assertions.assertEquals(VALID_MEMBER_NAME, bug.getAssignee().getName());
    }

    @Test
    public void Constructor_Should_ThrowError_When_ShorterTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, SHORTER_BUG_TITLE, VALID_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void Constructor_Should_ThrowError_When_LongerTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, LONGER_BUG_TITLE, VALID_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void Constructor_Should_ThrowError_When_ShorterDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_BUG_TITLE, SHORTER_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void Constructor_Should_ThrowError_When_LongerDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_BUG_TITLE, LONGER_BUG_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void getStepsToReproduce_Should_ReturnACopyOfTheOriginalList() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act, Assert*/
        Assertions.assertEquals(0, bug.getStepsToReproduce().size());
    }

    @Test
    public void print_Should_PrintCorrectMessage() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act, Assert*/
        Assertions.assertEquals(VALID_BUG_PRINT, bug.print());
    }

    @Test
    public void addStepsToReproduce_Should_AddListOfStepsToTheBug_When_ValidStepsPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
        List<String> stepsToReproduce = new ArrayList<>();
        stepsToReproduce.add(STEP_1);
        stepsToReproduce.add(STEP_2);

        /*Act*/
        bug.addStepsToReproduce(stepsToReproduce);

        /*Assert*/
        Assertions.assertEquals(2, bug.getStepsToReproduce().size());
        Assertions.assertEquals(STEP_1, bug.getStepsToReproduce().get(0));
        Assertions.assertEquals(STEP_2, bug.getStepsToReproduce().get(1));

    }


    @Test
    public void changeBugStatus_Should_ChangeStatus() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugStatus();

        /*Assert*/
        Assertions.assertEquals(BugStatusType.DONE.toString(), bug.getStatus().toString());
    }

    @Test
    public void changeBugPriority_Should_ChangePriority() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugPriority(BugPriorityType.MEDIUM);

        /*Assert*/
        Assertions.assertEquals(BugPriorityType.MEDIUM.toString(), bug.getPriority().toString());
    }

    @Test
    public void changeBugSeverity_Should_ChangeSeverity() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeBugSeverity(BugSeverityType.MINOR);

        /*Assert*/
        Assertions.assertEquals(BugSeverityType.MINOR.toString(), bug.getSeverity().toString());
    }

    @Test
    public void changeAssignee_Should_ChangeAssignee_When_ValidAssigneePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Member member2 = new MemberImpl(VALID_MEMBER2_NAME);
        Bug bug = new BugImpl(1, VALID_BUG_TITLE, VALID_BUG_DESCRIPTION,
                BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);

        /*Act*/
        bug.changeAssignee(member2);

        /*Assert*/
        Assertions.assertEquals(VALID_MEMBER2_NAME, bug.getAssignee().getName());
    }

}
