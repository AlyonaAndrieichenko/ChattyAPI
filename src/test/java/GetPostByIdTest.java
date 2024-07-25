import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static apiUtil.ApiRequests.getRequest;
import static apiUtil.ApiRequests.postRequest;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static post.PostDataCreation.getCreatedPost;

public class GetPostByIdTest extends AdminUserTest{

    @Test
    public void getPostByIdTest() {
        String postIdCreatedPost = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken)
                .getBody().jsonPath().getString("id");
        String postIdFromResponse = getRequest(GET_POST_BY_ID_PATH + postIdCreatedPost, 200, accessToken)
                .getBody().jsonPath().getString("id");
        assertEquals(postIdCreatedPost, postIdFromResponse);
    }

    @Test
    public void getPostByIdNoAuthorizationTest() {
        String postIdCreatedPost = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken)
                .getBody().jsonPath().getString("id");
        Response getPostByIdResponse = getRequest(GET_POST_BY_ID_PATH + postIdCreatedPost, 401, null);
        String errorMessage = getPostByIdResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getPostByIdBadRequestTest(){
        getRequest(GET_POST_BY_ID_PATH + "61edbb37-1702-4272-8b9c-f8620ed2ef1", 404, accessToken);
    }

}
