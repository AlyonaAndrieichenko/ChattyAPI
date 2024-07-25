import feedback.Feedback;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import post.Post;

import static apiUtil.ApiRequests.deleteRequest;
import static apiUtil.ApiRequests.postRequest;
import static apiUtil.UrlUtil.*;
import static feedback.FeedbackCreation.getCreatedFeedback;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static post.PostDataCreation.getCreatedPost;

public class SendFeedbackTest extends AdminUserTest {

    @Test
    public void sendFeedbackTest() {
        Feedback expected = getCreatedFeedback();
        Feedback postResponse = postRequest(POST_FEEDBACK_PATH, expected, 201, accessToken).body().as(Feedback.class);
        assertEquals(expected, postResponse);
    }

    @Test
    public void sendFeedbackNoAuthorizationTest() {
        Response createFeedbackResponse = postRequest(POST_FEEDBACK_PATH, getCreatedFeedback(), 401, null);
        String errorMessage = createFeedbackResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void sendFeedbackBadRequestTest(){
        Response createFeedbackResponse = postRequest(POST_FEEDBACK_PATH, getCreatedFeedback()+"", 400, accessToken);
        String errorMessage = createFeedbackResponse.getBody().jsonPath().getString("httpStatus");
        assertEquals("BAD_REQUEST", errorMessage);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}


