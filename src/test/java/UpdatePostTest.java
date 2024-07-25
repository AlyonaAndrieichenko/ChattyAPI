import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import post.Post;
import user.User;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static post.PostDataCreation.getCreatedPost;
import static post.PostDataCreation.getUpdatedPost;
import static user.UserDataRegistry.getUpdatedUser;

public class UpdatePostTest extends AdminUserTest{

    @Test
    public void updatePostTest() {
        Post created = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken)
                .body().as(Post.class);
        Post updated = putRequest(GET_POST_BY_ID_PATH + created.getId(), getUpdatedPost(), 200, accessToken)
                .body().as(Post.class);

        assertEquals(created.getId(), updated.getId());
        assertNotEquals(created.getTitle(), updated.getTitle());
        assertNotEquals(created.getDescription(), updated.getDescription());
        assertNotEquals(created.getBody(), updated.getBody());
        assertNotEquals(created.getImageUrl(), updated.getImageUrl());

        deletePost(updated.getId());
    }

    @Test
    public void updatePostNoAuthorizationTest() {
        String createdPostId = postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedPost(), 201, accessToken)
                .getBody().jsonPath().getString("id");
        Response updated = putRequest(GET_POST_BY_ID_PATH + createdPostId, getUpdatedPost(), 401, null);
        String errorMessage = updated.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
        deletePost(createdPostId);
    }

    @Test
    public void updatePostBadRequestTest(){
        putRequest(GET_POST_BY_ID_PATH + "61edbb37-1702-4272-8b9c-f8620ed2ef1", getUpdatedPost(), 404, accessToken);
    }

    public void deletePost(String postId){
        deleteRequest(GET_POST_BY_ID_PATH, 204,  accessToken, postId);
    }
}
