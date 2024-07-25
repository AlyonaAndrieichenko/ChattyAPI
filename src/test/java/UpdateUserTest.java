import apiUtil.ApiRequests;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import user.User;

import static apiUtil.ApiRequests.*;
import static apiUtil.UrlUtil.GET_USER_PATH;
import static apiUtil.UrlUtil.UPDATE_OR_DELETE_USER_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static user.UserDataRegistry.getUpdatedUser;


public class UpdateUserTest extends AdminUserTest {

    @Test
    public void updateUserTest() {
        User expected = getUpdatedUser(authUser.getEmail());
        putRequest(UPDATE_OR_DELETE_USER_PATH + userId, expected, 200, accessToken);
        User userResponse = getRequest(GET_USER_PATH, 200, accessToken).body().as(User.class);

        assertEquals(expected.getAvatarUrl(), userResponse.getAvatarUrl());
        assertEquals(expected.getName(), userResponse.getName());
        assertEquals(expected.getSurname(), userResponse.getSurname());
        assertEquals(userId, userResponse.getId());
        assertEquals(expected.getGender(), userResponse.getGender());
        assertEquals(expected.getPhone(), userResponse.getPhone());
        assertEquals(expected.getBirthDate(), userResponse.getBirthDate());
        assertFalse(userResponse.getEmail().isEmpty());
        assertFalse(userResponse.getRole().isEmpty());
        assertFalse(userResponse.getBlocked());
        assertEquals(expected.getBackgroundUrl(), userResponse.getBackgroundUrl());
    }

    @Test
    public void updateUserNoAuthorizationTest() {
        Response updateUserResponse = putRequest(UPDATE_OR_DELETE_USER_PATH, getUpdatedUser(authUser.getEmail()), 401, null);
        String errorMessage = updateUserResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void updateUserBadRequestTest(){
        putRequest(UPDATE_OR_DELETE_USER_PATH, "", 404, accessToken);
    }

    @AfterEach
    public void deleteUser(){
        deleteRequest(UPDATE_OR_DELETE_USER_PATH, 204,  accessAdminToken, userId);
    }
}
