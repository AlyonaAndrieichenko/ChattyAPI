import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import post.Post;

import java.util.List;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static post.PostDataCreation.getCreatedPost;

public class GetPostsByUserTest extends AdminUserTest{

    @Test
    public void getPostsByUserTest() {
        Post firstPost = createPostRequest();
        Post secondPost = createPostRequest();
        String authorId = List.of(firstPost.getUserId(), secondPost.getUserId()).toString();
        String getUserIdFromResponse = getRequest(UPDATE_OR_DELETE_USER_PATH + userId +
                GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 200, accessToken)
                .getBody().jsonPath().getString("user.id");
        assertEquals(authorId, getUserIdFromResponse);
    }

    @Test
    public void getPostsByUserNoAuthorizationTest() {
        Response getPostsByUserResponse = getRequest(UPDATE_OR_DELETE_USER_PATH + userId +
                GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 401, null);
        String errorMessage = getPostsByUserResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getPostsByUserBadRequestTest(){
        getRequest(UPDATE_OR_DELETE_USER_PATH + GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH,
                400, accessToken);
    }

    private Post createPostRequest(){
        return postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken).body().as(Post.class);
    }
}
