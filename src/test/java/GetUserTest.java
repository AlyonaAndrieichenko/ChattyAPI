import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static apiUtil.ApiRequests.getRequest;
import static apiUtil.UrlUtil.GET_USER_PATH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static user.UserRole.ADMIN;

public class GetUserTest extends AdminUserTest{

    @Test
    public void getUserTest() {
        Response getUserResponse = getRequest(GET_USER_PATH, 200, accessToken);
        assertEquals(getUserIdAfterRequest(), getUserResponse.getBody().jsonPath().getString("id"));
    }


    @Test
    public void getUserNoAuthorizationTest() {
        Response getUserResponse = getRequest(GET_USER_PATH, 401, null);
        String errorMessage = getUserResponse.getBody().jsonPath().getString("message");
        assertEquals("Unauthorized", errorMessage);
    }

    @Test
    public void getUserBadRequestTest(){
        getRequest(GET_USER_PATH + "9", 404, accessToken);
    }
}
