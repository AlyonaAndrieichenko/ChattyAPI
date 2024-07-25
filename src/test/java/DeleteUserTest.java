import static apiUtil.ApiRequests.deleteRequest;
import static apiUtil.ApiRequests.getRequest;
import static apiUtil.UrlUtil.GET_USER_PATH;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static apiUtil.UrlUtil.UPDATE_OR_DELETE_USER_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static user.UserRole.ADMIN;

public class DeleteUserTest extends AdminUserTest {

    @Test
    public void deleteUserTest() {
       deleteUserByAdmin();
       Response getUserResponse = getRequest(GET_USER_PATH, 404, accessToken);
       String errorMessage = getUserResponse.getBody().jsonPath().getString("message");
       assertEquals("User not found!", errorMessage);
    }


    @Test
    public void deleteUserNoAuthorizationTest() {
        Response deleteUserResponse = deleteRequest(UPDATE_OR_DELETE_USER_PATH, 401, null, userId);
        String errorMessage = deleteUserResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
        deleteUserByAdmin();
    }


    @Test
    public void deleteUserBadRequestTest(){
        setTokensAfterUserRegistration(ADMIN);
        Response deleteResponse = deleteRequest(UPDATE_OR_DELETE_USER_PATH, 404, accessToken, "");
        assertEquals(404, deleteResponse.getStatusCode());
        deleteUserByAdmin();
    }

    private void deleteUserByAdmin(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
