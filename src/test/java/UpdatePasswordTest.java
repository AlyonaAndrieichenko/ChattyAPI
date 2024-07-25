import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import user.Password;

import static apiUtil.ApiRequests.deleteRequest;
import static apiUtil.ApiRequests.putRequest;
import static apiUtil.UrlUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static user.UserDataRegistry.updatedPassword;

public class UpdatePasswordTest extends AdminUserTest{

    @Test
    public void updatePasswordTest() {
        Password expected = updatedPassword(authUser);
        putRequest(UPDATE_PASSWORD_PATH, expected, 200, accessToken);
        assertEquals(expected.getNewPassword(), authUser.getPassword());
    }

    @Test
    public void updatePasswordNoAuthorizationTest() {
        Response updatePasswordResponse = putRequest(UPDATE_OR_DELETE_USER_PATH, updatedPassword(authUser), 401, null);
        String errorMessage = updatePasswordResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void updatePasswordBadRequestTest(){
        putRequest(UPDATE_OR_DELETE_USER_PATH, "", 404, accessToken);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
