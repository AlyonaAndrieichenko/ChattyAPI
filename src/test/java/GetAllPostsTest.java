import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import post.Post;

import java.util.List;

import static apiUtil.ApiRequests.getRequest;
import static apiUtil.ApiRequests.postRequest;
import static apiUtil.UrlUtil.GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH;
import static apiUtil.UrlUtil.GET_POST_BY_ID_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static post.PostDataCreation.getCreatedPost;

public class GetAllPostsTest extends AdminUserTest{

    @Test
    public void getAllPostsTest() {
        getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 200, accessToken);


    }

    @Test
    public void getAllPostsNoAuthorizationTest() {
        Response getPostByIdResponse = getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, 401, null);
        String errorMessage = getPostByIdResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getAllPostsBadRequestTest(){
        getRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH +"/", 404, accessToken);
    }

}
