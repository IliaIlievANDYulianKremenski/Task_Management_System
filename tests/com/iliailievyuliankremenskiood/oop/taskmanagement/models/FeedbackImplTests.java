package com.iliailievyuliankremenskiood.oop.taskmanagement.models;

import com.iliailievyuliankremenskiood.oop.taskmanagement.models.contracts.Feedback;
import com.iliailievyuliankremenskiood.oop.taskmanagement.models.enums.feedbackrelatedtypes.FeedbackStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackImplTests {

    /*<-------Constant(s)------->*/
    private static final String VALID_FEEDBACK_TITLE = "The program freezes when the Log In button is clicked.";
    private static final String VALID_FEEDBACK_DESCRIPTION = "This needs to be fixed quickly!";
    private static final String SHORTER_FEEDBACK_TITLE = "123";
    private static final String LONGER_FEEDBACK_TITLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nulla vel mi ut quam rutrum vulputate. Ut in turpis luctus, elementum nisi sed, facilisis enim. " +
            "Duis porta quam orci, ac hendrerit enim molestie at. Sed pellentesque blandit ipsum. " +
            "Vivamus feugiat nibh nec nisl aliquam, sed bibendum lectus pharetra. Mauris iaculis suscipit sem " +
            "id pulvinar. Aenean pharetra quam sed sagittis accumsan. Cras accumsan lacus ac est pulvinar consectetur. " +
            "Mauris eget libero eros. Pellentesque odio odio, hendrerit a magna in, dapibus tincidunt enim. " +
            "Quisque mattis purus lacus, a venenatis purus maximus ac. Suspendisse potenti. Nam placerat imperdiet " +
            "velit, ut efficitur ex accumsan id. Donec eu magna vel nunc porttitor viverra. In id vehicula. ";

    private static final String SHORTER_FEEDBACK_DESCRIPTION = "123456789";
    private static final String LONGER_FEEDBACK_DESCRIPTION =
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
    private static final int VALID_FEEDBACK_RATING = 10;
    private static final FeedbackStatusType VALID_FEEDBACK_STATUS = FeedbackStatusType.NEW;
    private static final int VALID_TASK_ID = 1;

    private static final String VALID_FEEDBACK_PRINT = String.format("""
                    --------------
                    Feedback:
                        Id: %d
                    	Title: %s
                    	Description: %s
                        Rating: %d
                        Status: %s
                    --------------""", VALID_TASK_ID, VALID_FEEDBACK_TITLE, VALID_FEEDBACK_DESCRIPTION,
            VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS.toString());


    /*<-------Test(s)------->*/
    @Test
    public void constructor_Should_CreateValidFeedbackObject_When_ValidParamsPassed() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Assert*/
        Assertions.assertNotNull(feedback);
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_ShorterTitlePassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, SHORTER_FEEDBACK_TITLE,
                            VALID_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_LongerTitlePassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, LONGER_FEEDBACK_TITLE,
                            VALID_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_ShorterDescriptionPassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                            SHORTER_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void constructor_Should_ThrowException_When_LongerDescriptionPassed() {
        /*Arrange, Act, Assert*/
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> {
                    Feedback feedback = new FeedbackImpl(
                            VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                            LONGER_FEEDBACK_DESCRIPTION,
                            VALID_FEEDBACK_RATING,
                            VALID_FEEDBACK_STATUS);
                });
    }

    @Test
    public void getId_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Assert*/
        Assertions.assertEquals(VALID_TASK_ID, feedback.getId());
    }

    @Test
    public void getTitle_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_TITLE, feedback.getTitle());
    }

    @Test
    public void getDescription_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_DESCRIPTION, feedback.getDescription());
    }

    @Test
    public void getRating_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_RATING, feedback.getRating());
    }

    @Test
    public void getStatus_should_ReturnTheCorrectId_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_STATUS, feedback.getStatus());
    }

    @Test
    public void print_should_ReturnTheCorrectPrint_When_ValidInstanceCreated() {
        /*Arrange, Act*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);
        /*Assert*/
        Assertions.assertEquals(VALID_FEEDBACK_PRINT, feedback.print());
    }

    @Test
    public void changeRating_Should_ChangeRating_When_NewRatingPassed() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act*/
        feedback.changeRating(100);

        /*Assert*/
        Assertions.assertEquals(100, feedback.getRating());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the 2nd element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_NewStatusPassed() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act*/
        feedback.changeStatus(FeedbackStatusType.SCHEDULED);

        /*Assert*/
        Assertions.assertEquals("SCHEDULED", feedback.getStatus().toString());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the 2nd element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

    @Test
    public void getActivityHistory_Should_ReturnACopyOfTasksActivityHistory_When_ValidInstanceCreated() {
        /*Arrange*/
        Feedback feedback = new FeedbackImpl(VALID_TASK_ID, VALID_FEEDBACK_TITLE,
                VALID_FEEDBACK_DESCRIPTION,
                VALID_FEEDBACK_RATING, VALID_FEEDBACK_STATUS);

        /*Act, Assert*/
        Assertions.assertNotNull(feedback.getActivityHistory());
        Assertions.assertEquals(1, feedback.getActivityHistory().size());
        /* ✏️ NOTE ✏️:
         * 1 - I tried testing the exact content of the first element of the ActivityHistory,
         * but it was more difficult than expected,
         * considering we are also logging the exact moment the log was created. */
    }

}
