import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import post.Post;

import java.io.File;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static post.PostDataCreation.getCreatedPost;

public class UploadImageTest extends AdminUserTest{

    @Test
    public void uploadImageTest() {
        String uploadedImage = postRequestFormData(UPLOAD_IMAGE_PATH, new File("src/documents/cat.png"), 201, accessToken)
                .body().jsonPath().getString("imageUrl");
        assertNotNull(uploadedImage);
    }

    @Test
    public void uploadImageNoAuthorizationTest() {
        Response uploadedImage = postRequestFormData(UPLOAD_IMAGE_PATH, new File("src/documents/cat.png"), 401, null);
        String errorMessage = uploadedImage.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void uploadImageBadRequestTest(){
        postRequestFormData(UPLOAD_IMAGE_PATH, new File(""), 401, accessToken);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
