import io.restassured.response.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import post.Post;

import java.util.List;

import static apiUtil.ApiRequests.deleteRequest;
import static apiUtil.ApiRequests.getRequest;
import static apiUtil.UrlUtil.GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH;
import static apiUtil.UrlUtil.UPDATE_OR_DELETE_USER_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetAllPostsTest extends AdminUserTest{

    @Test
    public void getAllPostsTest() {
        List<Post> posts = getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 200, accessToken)
                .jsonPath().getList("", Post.class);
        assertTrue(posts.size() > 1);
    }

    @Test
    public void getAllPostsNoAuthorizationTest() {
        Response getAllPostsResponse = getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 401, null);
        String errorMessage = getAllPostsResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getAllPostsBadRequestTest(){
        getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH +"/", 404, accessToken);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
