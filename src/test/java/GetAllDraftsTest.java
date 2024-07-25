import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import post.Post;

import java.util.List;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static post.PostDataCreation.getCreatedDraft;

public class GetAllDraftsTest extends AdminUserTest{

    @Test
    public void getAllDraftsTest() {
        Post firstDraft = createDraftRequest();
        Post secondDraft = createDraftRequest();
        List<Post> drafts = List.of(secondDraft, firstDraft);
        List<Post> posts = getRequest(GET_DRAFT_POSTS_PATH, 200, accessToken)
                .jsonPath().getList("", Post.class);
        assertEquals(posts.size(), drafts.size());
        posts.forEach(post -> assertTrue(post.isDraft()));

        drafts.forEach(post -> deletePost(post.getId()));
    }

    @Test
    public void getAllDraftsNoAuthorizationTest() {
        Response getAllPostsResponse = getRequest(GET_DRAFT_POSTS_PATH, 401, null);
        String errorMessage = getAllPostsResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getAllDraftsBadRequestTest(){
        getRequest(GET_DRAFT_POSTS_PATH +"/", 404, accessToken);
    }

    private Post createDraftRequest(){
        return postRequest(GET_OR_PUT_OR_DELETE_OR_POST_POSTS_PATH, getCreatedDraft(), 201, accessToken).body().as(Post.class);
    }


    public void deletePost(String postId){
        deleteRequest(GET_POST_BY_ID_PATH, 204,  accessToken, postId);
    }

}
