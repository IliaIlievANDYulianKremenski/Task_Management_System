package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Bug;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Member;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Story;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.bugrelatedtypes.BugSeverityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryPriorityType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StorySizeType;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.storyrelatedtypes.StoryStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_STORY_TITLE = "The program freezes when the Log In button is clicked.";
    private static final String VALID_STORY_DESCRIPTION = "This needs to be fixed quickly!";
    private static final String VALID_MEMBER_NAME = "Юлиан Кременски";
    private static final String VALID_MEMBER2_NAME = "Илия Илиев";
    private static final String SHORTER_STORY_TITLE = "123";
    private static final String LONGER_STORY_TITLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nulla vel mi ut quam rutrum vulputate. Ut in turpis luctus, elementum nisi sed, facilisis enim. " +
            "Duis porta quam orci, ac hendrerit enim molestie at. Sed pellentesque blandit ipsum. " +
            "Vivamus feugiat nibh nec nisl aliquam, sed bibendum lectus pharetra. Mauris iaculis suscipit sem " +
            "id pulvinar. Aenean pharetra quam sed sagittis accumsan. Cras accumsan lacus ac est pulvinar consectetur. " +
            "Mauris eget libero eros. Pellentesque odio odio, hendrerit a magna in, dapibus tincidunt enim. " +
            "Quisque mattis purus lacus, a venenatis purus maximus ac. Suspendisse potenti. Nam placerat imperdiet " +
            "velit, ut efficitur ex accumsan id. Donec eu magna vel nunc porttitor viverra. In id vehicula. ";

    private static final String SHORTER_STORY_DESCRIPTION = "123456789";
    private static final String LONGER_STORY_DESCRIPTION =
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

    private static final String VALID_STORY_PRINT = String.format("""
            --------------
            Story:
                Id: %d
            	Title: %s
            	Description: %s
                Priority: %s
                Size: %s
                Status: %s
                Assignee: %s
            --------------""", 1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH.toString(),
                StorySizeType.LARGE.toString(),StoryStatusType.IN_PROGRESS.toString(), VALID_MEMBER_NAME);

    @Test
    public void constructor_Should_CreateAValidStory_When_ValidArgumentsPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS,member);

        Assertions.assertNotNull(story);
        Assertions.assertEquals(1, story.getId());
        Assertions.assertEquals(VALID_STORY_TITLE, story.getTitle());
        Assertions.assertEquals(VALID_STORY_DESCRIPTION, story.getDescription());
        Assertions.assertEquals(StoryPriorityType.HIGH.toString(), story.getPriority().toString());
        Assertions.assertEquals(StorySizeType.LARGE.toString(), story.getSize().toString());
        Assertions.assertEquals(VALID_MEMBER_NAME, story.getAssignee().getName());
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, SHORTER_STORY_TITLE, VALID_STORY_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void constructor_Should_ThrowError_When_LongerTitlePassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, LONGER_STORY_TITLE, VALID_STORY_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void constructor_Should_ThrowError_When_ShorterDescriptionPassed() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);


        /*Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Bug bug = new BugImpl(1, VALID_STORY_TITLE, SHORTER_STORY_DESCRIPTION,
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
                    Bug bug = new BugImpl(1, VALID_STORY_TITLE, LONGER_STORY_DESCRIPTION,
                            BugPriorityType.HIGH, BugSeverityType.CRITICAL, member);
                });
    }

    @Test
    public void getStatus_Should_ReturnValidStatus() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StoryStatusType.IN_PROGRESS.toString(), story.getStatus().toString());
    }

    @Test
    public void getPriority_Should_ReturnValidPriority() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StoryPriorityType.HIGH.toString(), story.getPriority().toString());
    }

    @Test
    public void getSize_Should_ReturnValidSize() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(StorySizeType.LARGE.toString(), story.getSize().toString());
    }

    @Test
    public void getAssignee_Should_ReturnValidAssignee() {
        /*Arrange*/
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act, Assess*/
        Assertions.assertEquals(VALID_MEMBER_NAME, story.getAssignee().getName());
    }

    @Test
    public void changeAssignee_Should_ChangeAssignee_When_ValidNewAssigneePassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Member member2 = new MemberImpl(VALID_MEMBER2_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeAssignee(member2);
        /*Assess*/
        Assertions.assertEquals(VALID_MEMBER2_NAME, story.getAssignee().getName());
    }

    @Test
    public void changeSize_Should_ChangeSize_When_ValidNewSizePassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeSize(StorySizeType.MEDIUM);
        /*Assess*/
        Assertions.assertEquals(StorySizeType.MEDIUM.toString(), story.getSize().toString());
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_ValidNewStatusPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changeStatus(StoryStatusType.DONE);
        /*Assess*/
        Assertions.assertEquals(StoryStatusType.DONE.toString(), story.getStatus().toString());
    }

    @Test
    public void changePriority_Should_ChangePriority_When_ValidNewPriorityPassed(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        /*Act*/
        story.changePriority(StoryPriorityType.LOW);
        /*Assess*/
        Assertions.assertEquals(StoryPriorityType.LOW.toString(), story.getPriority().toString());
    }

    @Test
    public void print_Should_CreateValidPrint(){
        Member member = new MemberImpl(VALID_MEMBER_NAME);
        Story story = new StoryImpl(1, VALID_STORY_TITLE, VALID_STORY_DESCRIPTION, StoryPriorityType.HIGH,
                StorySizeType.LARGE, StoryStatusType.IN_PROGRESS, member);

        Assertions.assertEquals(VALID_STORY_PRINT, story.print());
    }

}
