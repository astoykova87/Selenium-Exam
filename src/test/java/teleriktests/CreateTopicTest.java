package teleriktests;

import com.telerik.pages.LoginPage;
import com.telerik.pages.TopicPage;
import com.telerik.pages.CreateTopicPage;
import com.telerik.pages.utils.DataGenerator;
import com.telerik.pages.LoginHelper;
import org.junit.jupiter.api.*;
import testframework.core.BaseWebTest;

public class CreateTopicTest extends BaseWebTest {
    private static LoginPage loginPage;
    private static CreateTopicPage createTopicPage;
    private static TopicPage topicPage;
    private static LoginHelper loginHelper;

    @BeforeAll
    public static void setUp() {
        loginPage = new LoginPage();
        createTopicPage = new CreateTopicPage();
        topicPage = new TopicPage();
        loginHelper = new LoginHelper(loginPage);

        loginHelper.loginWithValidCredentials("armine.stoykova.a61@learn.telerikacademy.com", "Katrin2018Dayana@");
    }

    @Test
    public void userAuthenticated_when_validCredentialsProvided() {
        boolean isLearningPlatformVisible = createTopicPage.isLearningPlatformLinkVisible();
        Assertions.assertTrue(isLearningPlatformVisible, "LEARNING PLATFORM link is not visible after login.");

        String title = DataGenerator.generateUniqueTitle();
        String description = DataGenerator.generateUniqueDescription();

        createTopicPage.navigate();
        createTopicPage.createNewTopic(title, description);

        Assertions.assertTrue(topicPage.isTopicCreatedSuccessfully(), "Topic was not created successfully");

        Assertions.assertTrue(title.length() >= 5 && title.length() <= 255, "Title length is not valid.");
        Assertions.assertTrue(description.length() >= 100, "Description length is not valid.");
    }

    @RepeatedTest(5)
    public void testCreateNewTopicMultipleTimes() throws InterruptedException {
        String title = DataGenerator.generateUniqueTitle();
        String description = DataGenerator.generateUniqueDescription();
        createTopicPage.navigate();
        createTopicPage.createNewTopic(title, description);
        Assertions.assertTrue(topicPage.isTopicCreatedSuccessfully(), "Topic was not created successfully");
        Thread.sleep(3000);
    }
}
