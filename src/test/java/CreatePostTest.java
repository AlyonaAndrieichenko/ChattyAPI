import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import post.Post;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static post.PostDataCreation.getCreatedPost;
import static user.UserDataRegistry.updatedPassword;

public class CreatePostTest extends AdminUserTest{

    @Test
    public void createPostTest() {
        Post expected = getCreatedPost();
        Post postResponse = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, expected, 201, accessToken).body().as(Post.class);
        assertEquals(expected.getTitle(), postResponse.getTitle());
        assertEquals(expected.getDescription(), postResponse.getDescription());
        assertEquals(expected.getBody(), postResponse.getBody());
        assertEquals(expected.getImageUrl(), postResponse.getImageUrl());
        assertNotNull(postResponse.getPublishDate());
        assertNotNull(postResponse.getUpdatedAt());
        assertNotNull(postResponse.getId());
        assertEquals(expected.isDraft(), postResponse.isDraft());
        assertEquals(userId, postResponse.getUserId());
        deleteRequest(GET_POST_BY_ID_PATH, 204,  accessToken, postResponse.getId());
    }

    @Test
    public void createPostNoAuthorizationTest() {
        Response createPostResponse = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 401, null);
        String errorMessage = createPostResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void createPostBadRequestTest(){
        postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, "getCreatedPost()", 400, accessToken);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
