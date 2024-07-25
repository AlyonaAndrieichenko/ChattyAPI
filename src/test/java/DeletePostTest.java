import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import post.Post;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static post.PostDataCreation.getCreatedPost;
import static post.PostDataCreation.getUpdatedPost;

public class DeletePostTest extends AdminUserTest{

    @Test
    public void deletePostTest() {
        Post created = createPostRequest();
        deleteRequest(GET_POST_BY_ID_PATH, 204, accessToken, created.getId());

        Response getUserResponse = getRequest(GET_POST_BY_ID_PATH + created.getId(), 404, accessToken);
        String errorMessage = getUserResponse.getBody().jsonPath().getString("message");
        assertEquals("Post not found!", errorMessage);
    }

    @Test
    public void deletePostNoAuthorizationTest() {
        Post createdPostId = createPostRequest();
        Response deleted = deleteRequest(GET_POST_BY_ID_PATH, 401, null, createdPostId.getId());
        String errorMessage = deleted.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);

    }

    @Test
    public void deletePostBadRequestTest(){
        Post createdPostId = createPostRequest();
        deleteRequest(GET_POST_BY_ID_PATH, 404, accessToken, createdPostId.getUserId());
    }

    private Post createPostRequest(){
        return postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken).body().as(Post.class);
    }
}
